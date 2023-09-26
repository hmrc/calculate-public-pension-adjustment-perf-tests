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

object BasicWorkFlowRequests extends Configuration {

  val calculateRoute: String                = s"$calculationUrl/public-pension-adjustment"
  val savingsStatementPageUrl: String       = "/pension-saving-statement"
  val resubmittingAdjustmentPageUrl: String = "/change-previous-adjustment"
  val reportingChangePageUrl: String        = "/charges"
  val checkYourAnswersPageUrl: String       = "/check-your-answers-setup"

  val navigateToSavingsStatementPage: HttpRequestBuilder =
    http("Navigate to savings-statement page")
      .get(calculateRoute + savingsStatementPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitRssReceivedConfirmation(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + savingsStatementPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToResubmittingAdjustmentPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + resubmittingAdjustmentPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitResubmittingConfirmation(value: String): HttpRequestBuilder =
    http("Resubmitting adjustment : " + value)
      .post(calculateRoute + resubmittingAdjustmentPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToReportingChangePage: HttpRequestBuilder =
    http("Navigate to ReportingChange")
      .get(calculateRoute + reportingChangePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReportingChangeConfirmation(value: String, value2: String): HttpRequestBuilder =
    http("What are you reporting : " + value)
      .post(calculateRoute + reportingChangePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value[0]", value)
      .formParam("value[1]", value2)
      .check(status.is(303))

  val navigateToCheckYourAnswersPage: HttpRequestBuilder =
    http("Navigate to checkYourAnswers Page")
      .get(calculateRoute + checkYourAnswersPageUrl)
      .check(status.is(200))

}
