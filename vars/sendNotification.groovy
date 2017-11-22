@Grab(group='com.squareup.okhttp3', module='okhttp', version='3.9.1')

import com.squareup.okhttp3.*

def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    def client = new OkHttpClient()
    def body = RequestBody.create(
        MediaType.parse("application/json; charset=utf-8"),
        '''
        {
            "text": "**${config.title}**\\r**URL**: ${config.url}\\r**Branch**: ${config.branch}\\r**Server**: ${config.server}\\r**Deployer**: ${config.deployer}"
        }
        '''
    )
    def request = new Request.Builder()
        .url(config.address)
        .post(body)
        .build()
    client.newCall(request).execute()
}