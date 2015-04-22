package com.github.metermaid.kaybot

import org.scalatra._
import scalate.ScalateSupport
import java.util.Random


class Kaybot extends KaybotStack {

	var compliments = Array("You're beautiful", "You're lovely", "I hope you have a lovely day")

	get("/") {
		<html>
			<body>
				<h1>Hello, world!</h1>
				<p>{generate_compliment()}</p>
			</body>
		</html>
	}

	def generate_compliment() = {
		val rand = new Random()
		val i = rand.nextInt(compliments.length)
		compliments(i)
	}

}
