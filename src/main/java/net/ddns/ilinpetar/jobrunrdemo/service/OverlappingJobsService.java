package net.ddns.ilinpetar.jobrunrdemo.service;

import static org.jobrunr.scheduling.JobBuilder.aJob;

import java.security.SecureRandom;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OverlappingJobsService {

    private final Random random = new SecureRandom();

    public OverlappingJobsService(JobScheduler jobScheduler) {
        for (int i = 0; i < 10; i++) {
            final int jobId = i;
            jobScheduler.create(
                aJob()
                    .withLabels("job-" + jobId)
                    .withDetails(() -> overlappingJob(jobId)));
        }
    }

    public void overlappingJob(int jobId) throws InterruptedException {
        var message = "Overlapping job " + jobId;
        log.info("{} >> started", message);
        // 5 sec +- 500ms
        Thread.sleep(5000L + random.nextLong(500));
        log.info("{} << ended", message);
    }
}
