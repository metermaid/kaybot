package com.github.metermaid.kaybot

import org.scalatra._
import scalate.ScalateSupport

class Kaybot extends KaybotStack {

	get("/") {
		<html>
			<body>
				<h1>Hello, world!</h1>
				<p>{generate_compliment()}</p>
			</body>
		</html>
	}

	def generate_compliment() = {
		"You're beautiful"
	}

}
