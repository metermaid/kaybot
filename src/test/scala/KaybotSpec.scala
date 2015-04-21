package com.github.metermaid.kaybot

import org.scalatra.test.scalatest._
import org.scalatest.FunSuiteLike

class KaybotSpec extends  ScalatraSuite with FunSuiteLike {
  addServlet(classOf[Kaybot], "/*")

  test("simple get") {
    get("/") {
      status should equal (200)
      body should include ("beautiful")
    }
  }
}
