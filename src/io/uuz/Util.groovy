@Grab(group='com.squareup.okhttp3', module='okhttp', version='3.9.1')
package io.uuz;

import com.squareup.okhttp3.*

class Util {
    static def getUserName(self) {
        def job = Jenkins.getInstance().getItemByFullName(self.env.JOB_BASE_NAME, Job.class)
        def build = job.getBuildByNumber(self.env.BUILD_ID as int)
        def userId = build.getCause(Cause.UserIdCause).getUserId()
        return userId
    }

    @steps
    static def sendNotification(address, title, url, branch, server, deployer) {
        def client = new OkHttpClient();
        def body = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            '''
            {
                "text": "**${title}**\\r**URL**: ${url}\\r**Branch**: ${branch}\\r**Server**: ${server}\\r**Deployer**: ${deployer}"
            }
            '''
        )
        def request = new Request.Builder()
            .url(address)
            .post(body)
            .build()
        client.newCall(request).execute()
    }
}
