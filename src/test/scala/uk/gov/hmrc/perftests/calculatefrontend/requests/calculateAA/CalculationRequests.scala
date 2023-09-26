/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration

object CalculationRequests extends Configuration {
  val expectedText1                    =
    "tr+th:contains('Total increase in tax charges')"
  val expectedText2                    =
    "h2:contains('It is not a resubmission')"
  val expectedText3                    =
    "h2:contains('6 April 2022 to 5 April 2023')"
  val calculateRoute: String           = s"$calculationUrl/public-pension-adjustment"
  val calculationResultPageUrl: String = "/calculation-result"

  val navigateToCalculationResultPage: HttpRequestBuilder =
    http("Navigate to calculationResult page ")
      .get(calculateRoute + calculationResultPageUrl)
      .check(status.is(200))
      .check(css(expectedText1).find.exists)
      .check(css(expectedText2).find.exists)
      // TODO Reinstate date check on calculation page.
//      .check(css(expectedText3).find.exists)
      .check(saveCsrfToken)

  def submitCalculationResultConfirmation(): HttpRequestBuilder =
    http("calculationResult")
      .post(calculateRoute + calculationResultPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

}
