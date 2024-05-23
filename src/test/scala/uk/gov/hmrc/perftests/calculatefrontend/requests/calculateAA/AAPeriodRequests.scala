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

class AAPeriodRequests(periodYear: String) extends Configuration {

  val period                                            = s"/$periodYear"
  val calculateRoute: String                            = s"$calculationUrl/public-pension-adjustment/annual-allowance"
  val scheme1: String                                   = "0"
  val scheme2: String                                   = "1"
  val scheme3: String                                   = "2"
  val whatYouWillNeedAaPageUrl: String                  = "/information"
  val memberMoreThanOnePensionPageUrl: String           = "/multiple-schemes"
  val pensionSchemeDetailsPageUrl: String               = "/scheme-name-reference"
  val pensionSchemeInputAmountsPageUrl: String          = "/pension-input-amount"
  val didYouPayAChargePageUrl: String                   = "/annual-allowance-charge"
  val addAnotherSchemePageUrl: String                   = "/pension-scheme-summary"
  val contributedOtherDbDcSchemePageUrl: String         = "/contributed-to-any-other-dc-or-db-scheme"
  val whichContributedDuringRemedyPeriodPageUrl: String = "/contributed-to-dc-or-db-scheme"
  val piaForDbPensionPageUrl: String                    = "/pension-input-amount-defined-benefit"
  val checkYourAnswersPeriodPageUrl: String             = "/check-answers"
  val whoPaidChargePageUrl: String                      = "/who-paid-charge"
  val howMuchChargePageUrl: String                      = "/charge-amount-you-paid"
  val whichSchemeDetailsPageUrl: String                 = "/select-scheme"
  val totalIncomePageUrl: String                        = "/total-income"
  val thresholdIncomePageUrl: String                    = "/threshold-income"

  val navigateToWhatYouWillNeedAaPage: HttpRequestBuilder =
    http("Navigate to whatYouWillNeedAaPageUrl page " + period)
      .get(calculateRoute + period + whatYouWillNeedAaPageUrl)
      .check(status.is(200))

  val navigateToMemberMoreThanOnePensionPage: HttpRequestBuilder =
    http("Navigate to memberMoreThanOnePensionPageUrl page " + period)
      .get(calculateRoute + period + memberMoreThanOnePensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitMemberMoreThanOnePensionConfirmation(value: String): HttpRequestBuilder =
    http("memberMoreThanOnePensionPageUrl : " + value)
      .post(calculateRoute + period + memberMoreThanOnePensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToFirstPensionSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeDetails page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionFirstSchemeDetailsConfirmation(schemeName: String, schemeTaxRef: String): HttpRequestBuilder =
    http("pensionSchemeDetails : " + schemeName + " " + schemeTaxRef)
      .post(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("schemeName", schemeName)
      .formParam("schemeTaxRef", schemeTaxRef)
      .check(status.is(303))

  val navigateToFirstPensionSchemeInputAmountsPageUrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmountsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstPensionSchemeInputAmountsPageUrlConfirmation(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmountsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToDidYouPayAChargePage: HttpRequestBuilder =
    http("Navigate to DidYouPayACharge page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + didYouPayAChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDidYouPayAChargeConfirmation(value: String): HttpRequestBuilder =
    http("DidYouPayACharge : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + didYouPayAChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAddAnotherSchemePage: HttpRequestBuilder =
    http("Navigate to AddAnotherScheme page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + addAnotherSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  val navigateToWhoPaidAnnualAllowanceChargePage: HttpRequestBuilder =
    http("Navigate to whoPaidAnnualAllowanceCharge page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + whoPaidChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhoPaidAnnualAllowanceChargeConfirmation(value: String): HttpRequestBuilder =
    http("whoPaidAnnualAllowanceCharge : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + whoPaidChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHowMuchYouPayChargePage: HttpRequestBuilder =
    http("Navigate to howMuchYouPayCharge page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + howMuchChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowMuchYouPayChargeConfirmation(value: String): HttpRequestBuilder =
    http("howMuchYouPayCharge : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + howMuchChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  def submitAddAnotherSchemeConfirmation(value: String): HttpRequestBuilder =
    http("AddAnotherScheme : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + addAnotherSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToSecondPensionSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeDetails page " + period + " Scheme " + scheme2)
      .get(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionSecondSchemeDetailsConfirmation(schemeName: String, schemeTaxRef: String): HttpRequestBuilder =
    http("pensionSchemeDetails : " + schemeName + " " + schemeTaxRef)
      .post(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("schemeName", schemeName)
      .formParam("schemeTaxRef", schemeTaxRef)
      .check(status.is(303))

  val navigateToSecondPensionSchemeInputAmountsPageUrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme2)
      .get(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmountsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionSecondSchemeInputAmountsPageUrlConfirmation(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmountsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToDidYouPayAChargeSecondSchemePage: HttpRequestBuilder =
    http("Navigate to DidYouPayACharge page " + period + " Scheme " + scheme2)
      .get(calculateRoute + period + "/pension-scheme-" + scheme2 + didYouPayAChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDidYouPayAChargeSecondSchemeConfirmation(value: String): HttpRequestBuilder =
    http("DidYouPayACharge : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme2 + didYouPayAChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAddAnotherSchemeTwoPage: HttpRequestBuilder =
    http("Navigate to AddAnotherScheme page " + period + " Scheme " + scheme2)
      .get(calculateRoute + period + "/pension-scheme-" + scheme2 + addAnotherSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAddAnotherSchemeTwoConfirmation(value: String): HttpRequestBuilder =
    http("AddAnotherScheme : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme2 + addAnotherSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToContributedOtherDbDcSchemePage: HttpRequestBuilder =
    http("Navigate to contributedOtherDbDcScheme page " + period)
      .get(calculateRoute + period + contributedOtherDbDcSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContributedOtherDbDcSchemeConfirmation(value: String): HttpRequestBuilder =
    http("contributedOtherDbDcScheme : " + value)
      .post(calculateRoute + period + contributedOtherDbDcSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhichContributedDuringRemedyPeriodPage: HttpRequestBuilder =
    http("Navigate to whichContributedDuringRemedyPeriodPageUrl page " + period)
      .get(calculateRoute + period + whichContributedDuringRemedyPeriodPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichContributedDuringRemedyPeriodConfirmation(value: String): HttpRequestBuilder =
    http("whichContributedDuringRemedyPeriodPageUrl : " + value)
      .post(calculateRoute + period + whichContributedDuringRemedyPeriodPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value[1]", value)
      .check(status.is(303))

  val navigateToPiaForDbPensionPage: HttpRequestBuilder =
    http("Navigate to piaForDbPension page " + period)
      .get(calculateRoute + period + piaForDbPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaForDbPensionConfirmation(value: String): HttpRequestBuilder =
    http("piaForDbPensionPageUrl : " + value)
      .post(calculateRoute + period + piaForDbPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToCheckYourAnswersPeriodPage: HttpRequestBuilder =
    http("Navigate to checkYourAnswersPeriod page " + period)
      .get(calculateRoute + period + checkYourAnswersPeriodPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(200))

  val navigateToWhichSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to whichSchemeDetailsPage page " + period)
      .get(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichSecondSchemeDetailsConfirmation(value: String): HttpRequestBuilder =
    http("whichSecondSchemeDetailsPage : " + value)
      .post(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhichSecondSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to whichSchemeDetailsPage page " + period)
      .get(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme2)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichSchemeDetailsConfirmation(value: String): HttpRequestBuilder =
    http("whichSchemeDetailsPage : " + value)
      .post(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTotalIncomePageUrlPage: HttpRequestBuilder =
    http("Navigate to totalIncome page " + period)
      .get(calculateRoute + period + totalIncomePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTotalIncomePageUrlConfirmation(value: String): HttpRequestBuilder =
    http("totalIncomePage : " + value)
      .post(calculateRoute + period + totalIncomePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToThresholdIncomePage: HttpRequestBuilder =
    http("Navigate to thresholdIncome page " + period)
      .get(calculateRoute + period + thresholdIncomePageUrl)
      .check(status.is(200))

  def submitThresholdIncomePageConfirmation(value: String): HttpRequestBuilder =
    http("thresholdIncome : " + value)
      .post(calculateRoute + period + thresholdIncomePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhichThirdSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to whichSchemeDetailsPage page " + scheme3)
      .get(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme3)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichThirdSchemeDetailsConfirmation(value: String): HttpRequestBuilder =
    http("whichSecondSchemeDetailsPage : " + value)
      .post(calculateRoute + period + whichSchemeDetailsPageUrl + "-" + scheme3)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToThirdPensionSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeDetails page " + period + " Scheme " + scheme3)
      .get(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme3)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionThirdSchemeDetailsConfirmation(schemeName: String, schemeTaxRef: String): HttpRequestBuilder =
    http("pensionSchemeDetails : " + schemeName + " " + schemeTaxRef)
      .post(calculateRoute + period + pensionSchemeDetailsPageUrl + "/" + scheme3)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("schemeName", schemeName)
      .formParam("schemeTaxRef", schemeTaxRef)
      .check(status.is(303))

  val navigateToThirdPensionSchemeInputAmountsPageUrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme3)
      .get(calculateRoute + period + "/pension-scheme-" + scheme3 + pensionSchemeInputAmountsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionThirdSchemeInputAmountsPageUrlConfirmation(
    originalPIA: String,
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + originalPIA + " " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme3 + pensionSchemeInputAmountsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("originalPIA", originalPIA)
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToDidYouPayAChargeThirdSchemePage: HttpRequestBuilder =
    http("Navigate to DidYouPayACharge page " + period + " Scheme " + scheme3)
      .get(calculateRoute + period + "/pension-scheme-" + scheme3 + didYouPayAChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDidYouPayAChargeThirdSchemeConfirmation(value: String): HttpRequestBuilder =
    http("DidYouPayACharge : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme3 + didYouPayAChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAddAnotherSchemeThreePage: HttpRequestBuilder =
    http("Navigate to AddAnotherScheme page " + period + " Scheme " + scheme3)
      .get(calculateRoute + period + "/pension-scheme-" + scheme3 + addAnotherSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAddAnotherSchemeThreeConfirmation(value: String): HttpRequestBuilder =
    http("AddAnotherScheme3 : " + value)
      .post(calculateRoute + period + "/pension-scheme-" + scheme3 + addAnotherSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))
}
