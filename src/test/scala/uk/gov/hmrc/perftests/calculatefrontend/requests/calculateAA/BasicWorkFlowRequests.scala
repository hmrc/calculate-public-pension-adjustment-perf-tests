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

package uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration

object BasicWorkFlowRequests extends Configuration {

  val calculateRoute: String                                     = s"$calculationUrl/public-pension-adjustment"
  val savingsStatementPageUrl: String                            = "/pension-saving-statement"
  val resubmittingAdjustmentPageUrl: String                      = "/change-previous-adjustment"
  val reportingChangePageUrl: String                             = "/charges"
  val checkYourAnswersPageUrl: String                            = "/check-your-answers-setup"
  val affectedByRemedy: String                                   = "/triage-journey/affected-by-remedy"
  val receivedLetter: String                                     = "/triage-journey/received-letter"
  val protectedMember: String                                    = "/triage-journey/protected-member"
  val annualAllowanceCharge: String                              = "/triage-journey/annual-allowance-charge"
  val contributionRefunds: String                                = "/triage-journey/contribution-refunds"
  val incomeOver100: String                                      = "/triage-journey/income-over-100"
  val incomeOver190_22: String                                   = "/triage-journey/income-over-190-22"
  val pIAAmountIncreased: String                                 = "/triage-journey/PIA-amount-increased"
  val pIAAmountDecreaseOrNoChange: String                        = "/triage-journey/PIA-amount-decrease-or-no-change"
  val pIAAboveAnnualAllowanceLimit: String                       = "/triage-journey/PIA-above-annual-allowance-limit"
  val incomeOver_190_23: String                                  = "/triage-journey/income-over-190-23"
  val flexibleAccessDCScheme: String                             = "/triage-journey/flexible-access-dc-scheme"
  val fourThousandContributionToDirectContributionScheme: String =
    "/triage-journey/4000-contribution-to-defined-contribution-scheme"

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

  val navigateToAffectedByRemedyPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + affectedByRemedy)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAffectedByRemedy(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + affectedByRemedy)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToReceivedLetterPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + receivedLetter)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReceivedLetter(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + receivedLetter)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectedMemberPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + protectedMember)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectedMember(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + protectedMember)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAnnualAllowanceChargePage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + annualAllowanceCharge)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAnnualAllowanceCharge(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + annualAllowanceCharge)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToContributionRefundsPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + contributionRefunds)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContributionRefunds(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + contributionRefunds)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToIncomeOver100Page: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + incomeOver100)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitIncomeOver100(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + incomeOver100)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToIncomeOver190_22Page: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + incomeOver190_22)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitIncomeOver190_22(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + incomeOver190_22)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPIAAmountIncreasedPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + pIAAmountIncreased)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPIAAmountIncreased(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + pIAAmountIncreased)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPIAAmountDecreaseOrNoChangePage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + pIAAmountDecreaseOrNoChange)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPIAAmountDecreaseOrNoChange(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + pIAAmountDecreaseOrNoChange)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPIAAboveAnnualAllowanceLimitPage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + pIAAboveAnnualAllowanceLimit)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPIAAboveAnnualAllowanceLimit(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + pIAAboveAnnualAllowanceLimit)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToIncomeOver190_23Page: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + incomeOver_190_23)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitIncomeOver190_23(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + incomeOver_190_23)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToFlexibleAccessDCSchemePage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + flexibleAccessDCScheme)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFlexibleAccessDCScheme(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + flexibleAccessDCScheme)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateTo4000ContributionToDirectContributionSchemePage: HttpRequestBuilder =
    http("Navigate to Resubmitting Adjustment page")
      .get(calculateRoute + fourThousandContributionToDirectContributionScheme)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submit4000ContributionToDirectContributionScheme(value: String): HttpRequestBuilder =
    http("Rss received : " + value)
      .post(calculateRoute + fourThousandContributionToDirectContributionScheme)
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
