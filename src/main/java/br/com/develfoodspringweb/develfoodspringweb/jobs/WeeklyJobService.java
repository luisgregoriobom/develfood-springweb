package br.com.develfoodspringweb.develfoodspringweb.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class WeeklyJobService {

    public static final long EXECUTION_TIME = 5000L;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AtomicInteger count = new AtomicInteger();

    public void executeSampleJob() {

        logger.info("Job será executado...");
        try {
            Thread.sleep(EXECUTION_TIME);
        } catch (InterruptedException e) {
            logger.error("Error while executing sample job", e);
        } finally {
            count.incrementAndGet();
            logger.info("Job executado com sucesso!");
        }
    }

    public int getNumberOfInvocations() {
        return count.get();
    }
}