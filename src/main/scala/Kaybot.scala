package com.github.metermaid.kaybot

import org.scalatra._
import scalate.ScalateSupport

import com.twilio.sdk.TwilioRestClient
import com.twilio.sdk.resource.instance.Sms

import scala.collection.JavaConversions._

class Kaybot extends KaybotStack {

	get("/") {
		<html>
			<body>
				<h1>Send me a Compliment</h1>
				<form action={url("/send")} method='POST'>
				  Phone Number: <input name='to' type='text'/><br />
				  <input type='submit'/>
	         </form>
			</body>
		</html>
	}

	post("/send") {
		val twilio_credentials = sys.env("TWILIO")
		val Array(sid, token, app_sid) = twilio_credentials.split(":")

		// Create a rest client
		val client = new TwilioRestClient(sid, token)

		val params = Map(("Body", generate_compliment()), ("To", request.getParameter("to").toString()), ("From", "+16123516529"))
		val message: Sms = client.getAccount.getSmsFactory.create(params.toMap)

		redirect("/")
	}

	private def generate_compliment() = {
		"You're beautiful"
	}

}
