package com.github.metermaid.kaybot

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike
import org.scalamock.scalatest.MockFactory

import com.twilio.sdk.TwilioRestClient
import com.twilio.sdk.resource.instance.Sms
import javax.servlet.http.HttpServletRequest

class KaybotSpec extends ScalatraSuite with FunSuiteLike with MockFactory {
  addServlet(classOf[Kaybot], "/*")

  test("simple get") {
    get("/") {
      status should equal (200)
      body should include ("send")
    }
  }

  test("send message") {
  		val twilio_credentials = stubFunction[String, String] // can't stub system, so here's this vague stuff
  		val request = stub[HttpServletRequest]

    	val fake_params = Map("Body" -> "You're beautiful", "To" -> "33333", "From" -> "+16123516529")

    	post("/post") {
    		twilio_credentials when "TWILIO" returns "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:3"
	  		(request.getParameter _) when("to") returns "33333"

    		status should be(302)
    	}
  }
}
