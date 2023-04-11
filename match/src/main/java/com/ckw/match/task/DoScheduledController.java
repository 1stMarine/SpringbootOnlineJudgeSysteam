package com.ckw.match.task;


import com.ckw.match.mapper.MatchMapper;
import com.ckw.match.pojo.Match;
import com.ckw.match.pojo.TaskScheduleModel;
import com.ckw.match.utils.CronUtil;
import com.ckw.match.utils.ScheduledFutureHolder;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@RestController
public class DoScheduledController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private MatchMapper matchMapper;

    //存储任务执行的包装类
    private HashMap<String, ScheduledFutureHolder> scheduleMap = new HashMap<>();

    /**
     *启动任务
     * 如果不想手动触发任务可以使用 @PostConstruct注解来启动
     */
//    @PostConstruct
    public void startTask()  {
        try {
            List<Match> startOrEndMatchs = matchMapper.getStartOrEndMatchs();
            for (Match match : startOrEndMatchs) {
                ScheduledFutureHolder scheduledFutureHolder = null;
                if(match.getState().equals("未开始")){
                    System.out.println("竞赛" + match.getMatchName() + " 未开始，进入开始竞赛监视");
                    MatchStartTask startTask = getStartTask(match);
                    String corn = getCorn(match.getStartTime());
                    //将任务交给任务调度器执行
                    ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(startTask, new CronTrigger(corn));

                    //将任务包装成ScheduledFutureHolder
                    scheduledFutureHolder = new ScheduledFutureHolder();
                    scheduledFutureHolder.setScheduledFuture(schedule);
                    scheduledFutureHolder.setRunnableClass(startTask.getClass());
                    scheduledFutureHolder.setCorn(corn);

                    MatchEndTask endTask = getMatchEndTask(match);
                    corn = getCorn(match.getEndTime());
                    //将任务交给任务调度器执行
                    schedule = threadPoolTaskScheduler.schedule(endTask, new CronTrigger(corn));

                    //将任务包装成ScheduledFutureHolder
                    scheduledFutureHolder = new ScheduledFutureHolder();
                    scheduledFutureHolder.setScheduledFuture(schedule);
                    scheduledFutureHolder.setRunnableClass(endTask.getClass());
                    scheduledFutureHolder.setCorn(corn);
                }
                else{
                    System.out.println("竞赛" + match.getMatchName() + " 已开始，进入结算竞赛监视");
                    MatchEndTask endTask = getMatchEndTask(match);
                    String corn = getCorn(match.getEndTime());
                    //将任务交给任务调度器执行
                    ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(endTask, new CronTrigger(corn));

                    //将任务包装成ScheduledFutureHolder
                    scheduledFutureHolder = new ScheduledFutureHolder();
                    scheduledFutureHolder.setScheduledFuture(schedule);
                    scheduledFutureHolder.setRunnableClass(endTask.getClass());
                    scheduledFutureHolder.setCorn(corn);
                }
                scheduleMap.put(scheduledFutureHolder.getRunnableClass().getName(),scheduledFutureHolder);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到比赛开始时间corn表达式
     * @param time
     * @return
     * @throws Exception
     */
    public String getCorn(String time) throws Exception {
        TaskScheduleModel scheduleModel = new TaskScheduleModel();
        scheduleModel.setJobType(5);
        scheduleModel.setStartDate(time);

        return CronUtil.createCronExpression(scheduleModel);
    }

    /**
     * 得到开始竞赛任务
     * @param match
     * @return
     */
    public MatchStartTask getStartTask(Match match){
        MatchStartTask matchStartTask = beanFactory.getBean(MatchStartTask.class);
        matchStartTask.match = match;
        return matchStartTask;
    }

    /**
     * 得到比赛结束任务
     * @param match
     * @return
     */
    public MatchEndTask getMatchEndTask(Match match){
        MatchEndTask matchEndTask = beanFactory.getBean(MatchEndTask.class);
        matchEndTask.match = match;
        return matchEndTask;
    }
    /**
     * 查询所有的任务
     */
    @RequestMapping("/queryTask")
    public void queryTask(){
        scheduleMap.forEach((k,v)->{
            System.out.println(k+"  "+v);
        });
    }

    /**
     * 停止任务
     * @param className
     */
    @RequestMapping("/stop/{className}")
    public void stopTask(@PathVariable  String className){
        if(scheduleMap.containsKey(className)){//如果包含这个任务
            ScheduledFuture<?> scheduledFuture = scheduleMap.get(className).getScheduledFuture();
            if(scheduledFuture!=null){
                scheduledFuture.cancel(true);
            }
        }
    }


    /**
     * 重启任务，修改任务的触发时间
     * @param className
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping("/restart/{className}")
    public void restartTask(@PathVariable String className) throws InstantiationException, IllegalAccessException {
        if(scheduleMap.containsKey(className)){//如果包含这个任务


            //这里的corn可以通过前端动态传过来
            String corn = "0/50 * * * *  ?";
            ScheduledFutureHolder scheduledFutureHolder = scheduleMap.get(className);
            ScheduledFuture<?> scheduledFuture = scheduledFutureHolder.getScheduledFuture();
            if(scheduledFuture!=null){
                //先停掉任务
                scheduledFuture.cancel(true);

                //修改触发时间重新启动任务
                Runnable runnable = scheduledFutureHolder.getRunnableClass().newInstance();

                ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(corn));

                scheduledFutureHolder.setScheduledFuture(schedule);
                scheduledFutureHolder.setCorn(corn);

                scheduleMap.put(scheduledFutureHolder.getRunnableClass().getName(),scheduledFutureHolder);
            }
        }
    }
}
