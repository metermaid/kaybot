package com.github.metermaid.kaybot

import org.scalatra._
import scalate.ScalateSupport
import java.util.Random

import com.twilio.sdk.TwilioRestClient
import com.twilio.sdk.resource.instance.Sms

import com.twilio.sdk.verbs.TwiMLResponse
import com.twilio.sdk.verbs.TwiMLException
import com.twilio.sdk.verbs.Message

import scala.collection.JavaConversions._

class Kaybot extends KaybotStack {
	
	private var compliments = Array("You're beautiful", "You're lovely", "I hope you have a lovely day")

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

	post("/sms") {
		contentType = "application/xml"

		val twiml = new TwiMLResponse()
		val message = new Message(generate_compliment())

		try {
			twiml.append(message);
		} catch {
			case twimlex: TwiMLException => twimlex.printStackTrace()
		}

		twiml.toXML()
	}

	notFound {
		response.sendError(404)
	}

	private def generate_compliment() = {
		val rand = new Random()
		val i = rand.nextInt(compliments.length)
		compliments(i)
	}

}
