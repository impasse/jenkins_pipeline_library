@Grab(group='com.squareup.okhttp3', module='okhttp', version='3.9.1')

import com.squareup.okhttp3.*

def call(address, title, url, branch, server, deployer) {
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