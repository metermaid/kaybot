import com.github.metermaid.kaybot._
import org.scalatra._
import javax.servlet.ServletContext
import org.scalatra.example.data.DatabaseInit

class ScalatraBootstrap extends LifeCycle with DatabaseInit {
  override def init(context: ServletContext) {
    configureDb()
    context.mount(new Kaybot, "/*")
  }
  
  override def destroy(context:ServletContext) {
    closeDbConnection()
  }
}
