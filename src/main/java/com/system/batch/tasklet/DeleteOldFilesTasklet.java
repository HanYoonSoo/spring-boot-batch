//package com.system.batch.tasklet;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//
//import java.io.File;
//
//@Slf4j
//public class DeleteOldFilesTasklet implements Tasklet {
//    private final String path;
//    private final int daysOld;
//
//    public DeleteOldFilesTasklet(String path, int daysOld) {
//        this.path = path;
//        this.daysOld = daysOld;
//    }
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
//        File dir = new File(path);
//        long cutoffTime = System.currentTimeMillis() - (daysOld * 24 * 60 * 60 * 1000L);
//
//        File[] files = dir.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.lastModified() < cutoffTime) {
//                    if (file.delete()) {
//                        log.info("🔥 파일 삭제: {}", file.getName());
//                    } else {
//                        log.info("⚠️  파일 삭제 실패: {}", file.getName());
//                    }
//                }
//            }
//        }
//        return RepeatStatus.FINISHED;
//    }
//}
//
//// FileCleanupBatchConfig
//@Bean
//public Tasklet deleteOldFilesTasklet() {
//    // "temp" 디렉토리에서 30일 이상 지난 파일 삭제
//    return new DeleteOldFilesTasklet("/path/to/temp", 30);
//}
//
//@Bean
//public Step deleteOldFilesStep() {
//    return new StepBuilder("deleteOldFilesStep", jobRepository)
//            .tasklet(deleteOldFilesTasklet(), transactionManager)
//            .build();
//}
//
//@Bean
//public Job deleteOldFilesJob() {
//    return new JobBuilder("deleteOldFilesJob", jobRepository)
//            .start(deleteOldFilesStep())
//            .build();
//}

//@Bean
//public Step deleteOldRecordsStep() {
//    return new StepBuilder("deleteOldRecordsStep", jobRepository)
//            .tasklet((contribution, chunkContext) -> {
//                int deleted = jdbcTemplate.update("DELETE FROM logs WHERE created < NOW() - INTERVAL 7 DAY");
//                log.info("🗑️ {}개의 오래된 레코드가 삭제되었습니다.", deleted);
//                return RepeatStatus.FINISHED;
//            }, transactionManager)
//            .build();
//}
//
//@Bean
//public Job deleteOldRecordsJob() {
//    return new JobBuilder("deleteOldRecordsJob", jobRepository)
//            .start(deleteOldRecordsStep())  // Step을 Job에 등록
//            .build();
//}
