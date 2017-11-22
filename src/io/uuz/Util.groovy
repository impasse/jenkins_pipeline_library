package io.uuz;

class Util {
    static def getUserName(self) {
        def job = Jenkins.getInstance().getItemByFullName(self.env.JOB_BASE_NAME, Job.class)
        def build = job.getBuildByNumber(self.env.BUILD_ID as int)
        def userId = build.getCause(Cause.UserIdCause).getUserId()
        return userId
    }
}
