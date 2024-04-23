package de.mathema.campus.multitenant.service.batch;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.CronTrigger;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.concurrent.Trigger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.time.ZoneId;

@ApplicationScoped()
public class BatchStarter {

    @Inject
    private StoreBatch storeBatch;

    @Resource
    private ManagedScheduledExecutorService scheduler;

    @PostConstruct
    public void initScheduler() {
        System.out.println("initScheduler");

        Trigger trigger = new CronTrigger("0/30 * * * * *", ZoneId.systemDefault());

        scheduler.schedule(this::startBatch, trigger);
        //scheduler.scheduleAtFixedRate(this::startBatch, 5, 30, TimeUnit.SECONDS);
    }

    public void initApllication(
            @Observes @Initialized(ApplicationScoped.class) Object init) {
        System.out.println("initApllication");

        startBatch();
    }

    private void startBatch() {
        System.out.println("start Batch");
        storeBatch.start();
    }
}