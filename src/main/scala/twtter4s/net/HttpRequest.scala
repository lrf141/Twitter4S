package twtter4s.net

import java.net.{HttpURLConnection,
                 URL,
                 URLEncoder}
import java.io.{BufferedReader,
                InputStream,
                PrintStream,
                InputStreamReader,
                IOException,
                OutputStream}


/**
  * Created by lrf141 on 17/08/26.
  * @author lrf141
  * @since 0.0.1
  */
class HttpRequest {

  private [this] val endpointBaseURL:String = "https://api.twitter.com/1.1/"
  private [this] val oauthRequestURL:String = "https://api.twitter.com/oauth/request_token"
  private [this] val encodeType:String = "UTF-8"

}
