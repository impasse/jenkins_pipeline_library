package io.uuz;

class Current {
    Current(script) {
        this.script = script
    }
    def getUserName() {
        def job = Jenkins.getInstance().getItemByFullName(this.script.env.JOB_BASE_NAME, Job.class)
        def build = job.getBuildByNumber(this.script.env.BUILD_ID as int)
        def userId = build.getCause(Cause.UserIdCause).getUserId()
        return userId
    }
}
