import com.github.metermaid.kaybot._

import com.mchange.v2.c3p0.ComboPooledDataSource
import org.squeryl.adapters.{H2Adapter, MySQLAdapter}
import org.squeryl.Session
import org.squeryl.SessionFactory
import org.slf4j.LoggerFactory

trait DatabaseInit {
  val logger = LoggerFactory.getLogger(getClass)

  val databaseURI = new URI(scala.util.Properties.envOrElse("DATABASE_URL", "postgres://postgres@localhost:5432" ))
  val databaseUsername = databaseURI.getUserInfo.split(":")(0)
  val databasePassword = databaseURI.getUserInfo.split(":")(1)
  val databaseConnection = "jdbc:postgresql://${dbUri.getHost}:${dbUri.getPort}${dbUri.getPath}"

  var cpds = new ComboPooledDataSource

  def configureDb() {
    cpds.setDriverClass("org.postgresql.Driver")
    cpds.setJdbcUrl(databaseConnection)
    cpds.setUser(databaseUsername)
    cpds.setPassword(databasePassword)

    cpds.setMinPoolSize(1)
    cpds.setAcquireIncrement(1)
    cpds.setMaxPoolSize(50)

    SessionFactory.concreteFactory = Some(() => connection)

    def connection = {
      logger.info("Creating connection with c3po connection pool")
      Session.create(cpds.getConnection, new H2Adapter)
    }
  }

  def closeDbConnection() {
    logger.info("Closing c3po connection pool")
    cpds.close()
  }
}