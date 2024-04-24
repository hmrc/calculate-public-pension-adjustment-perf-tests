/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.calculatefrontend.requests.showCalculation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests.finalSubmissionBackendUrl

import scala.io.Source

object ShowCalculationRequests extends Configuration {

  val finalCalculationRoute: String = s"$finalShowCalculationUrl/calculate-public-pension-adjustment"
  val showCalculationPageUrl = "/show-calculation"
  val jsonPayload            = Source.fromResource("data/showCalculation.json").getLines().mkString

  val sessionHeaders =
    Map(
      "Authorization" -> "123",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.P1.0+json",
      "CorrelationId" -> "12345678"
    )

  def verifyShowCalculationResults(): HttpRequestBuilder =
    http("Show Calculation")
      .post(finalCalculationRoute + showCalculationPageUrl)
      .headers(sessionHeaders)
      .body(StringBody(jsonPayload))
      .check(status.is(200))
}
