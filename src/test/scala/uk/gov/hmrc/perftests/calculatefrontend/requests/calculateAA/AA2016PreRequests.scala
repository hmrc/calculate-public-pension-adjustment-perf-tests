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

object AA2016PreRequests extends Configuration {

  val calculateRoute: String                            = s"$calculationUrl/public-pension-adjustment"
  val period2016Pre: String                             = "/2016-pre"
  val scheme1: String                                   = "/0"
  val scheme2: String                                   = "/1"
  val whatYouWillNeedAaPageUrl: String                  = "/what-you-will-need-aa"
  val memberMoreThanOnePensionPageUrl: String           = "/member-more-than-one-pension"
  val pensionSchemeDetailsPageUrl: String               = "/pension-scheme-details"
  val pensionSchemeInputAmountsPageUrl: String          = "/pension-scheme-input-amounts"
  val didYouPayAChargePageUrl: String                   = "/did-you-pay-a-charge"
  val addAnotherSchemePageUrl: String                   = "/add-another-scheme"
  val contributedOtherDbDcSchemePageUrl: String         = "/contributed-other-db-dc-scheme"
  val whichContributedDuringRemedyPeriodPageUrl: String = "/which-contributed-during-remedy-period"
  val piaForDbPensionPageUrl: String                    = "/pia-for-db-pension"
  val checkYourAnswersPeriodPageUrl: String             = "/check-your-answers-period"

  val navigateToWhatYouWillNeedAaPage: HttpRequestBuilder =
    http("Navigate to whatYouWillNeedAaPageUrl page " + period2016Pre)
      .get(calculateRoute + whatYouWillNeedAaPageUrl + period2016Pre)
      .check(status.is(200))

  val navigateToMemberMoreThanOnePensionPage: HttpRequestBuilder =
    http("Navigate to memberMoreThanOnePensionPageUrl page " + period2016Pre)
      .get(calculateRoute + memberMoreThanOnePensionPageUrl + period2016Pre)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitMemberMoreThanOnePensionConfirmation(value: String): HttpRequestBuilder =
    http("memberMoreThanOnePensionPageUrl : " + value)
      .post(calculateRoute + memberMoreThanOnePensionPageUrl + period2016Pre)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToFirstPensionSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeDetails page " + period2016Pre + " Scheme " + scheme1)
      .get(calculateRoute + pensionSchemeDetailsPageUrl + period2016Pre + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionFirstSchemeDetailsConfirmation(schemeName: String, schemeTaxRef: String): HttpRequestBuilder =
    http("pensionSchemeDetails : " + schemeName + " " + schemeTaxRef)
      .post(calculateRoute + pensionSchemeDetailsPageUrl + period2016Pre + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("schemeName", schemeName)
      .formParam("schemeTaxRef", schemeTaxRef)
      .check(status.is(303))

  val navigateToFirstPensionSchemeInputAmountsPageUrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period2016Pre + " Scheme " + scheme1)
      .get(calculateRoute + pensionSchemeInputAmountsPageUrl + period2016Pre + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstPensionSchemeInputAmountsPageUrlConfirmation(
    originalPIA: String,
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + originalPIA + " " + revisedPIA)
      .post(calculateRoute + pensionSchemeInputAmountsPageUrl + period2016Pre + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("originalPIA", originalPIA)
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToDidYouPayAChargePage: HttpRequestBuilder =
    http("Navigate to DidYouPayACharge page " + period2016Pre + " Scheme " + scheme1)
      .get(calculateRoute + didYouPayAChargePageUrl + period2016Pre + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDidYouPayAChargeConfirmation(value: String): HttpRequestBuilder =
    http("DidYouPayACharge : " + value)
      .post(calculateRoute + didYouPayAChargePageUrl + period2016Pre + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAddAnotherSchemePage: HttpRequestBuilder =
    http("Navigate to AddAnotherScheme page " + period2016Pre + " Scheme " + scheme1)
      .get(calculateRoute + addAnotherSchemePageUrl + period2016Pre + scheme1)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAddAnotherSchemeConfirmation(value: String): HttpRequestBuilder =
    http("AddAnotherScheme : " + value)
      .post(calculateRoute + addAnotherSchemePageUrl + period2016Pre + scheme1)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToSecondPensionSchemeDetailsPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeDetails page " + period2016Pre + " Scheme " + scheme2)
      .get(calculateRoute + pensionSchemeDetailsPageUrl + period2016Pre + scheme2)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionSecondSchemeDetailsConfirmation(schemeName: String, schemeTaxRef: String): HttpRequestBuilder =
    http("pensionSchemeDetails : " + schemeName + " " + schemeTaxRef)
      .post(calculateRoute + pensionSchemeDetailsPageUrl + period2016Pre + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("schemeName", schemeName)
      .formParam("schemeTaxRef", schemeTaxRef)
      .check(status.is(303))

  val navigateToSecondPensionSchemeInputAmountsPageUrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period2016Pre + " Scheme " + scheme2)
      .get(calculateRoute + pensionSchemeInputAmountsPageUrl + period2016Pre + scheme2)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionSecondSchemeInputAmountsPageUrlConfirmation(
    originalPIA: String,
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + originalPIA + " " + revisedPIA)
      .post(calculateRoute + pensionSchemeInputAmountsPageUrl + period2016Pre + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("originalPIA", originalPIA)
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToDidYouPayAChargeSecondSchemePage: HttpRequestBuilder =
    http("Navigate to DidYouPayACharge page " + period2016Pre + " Scheme " + scheme2)
      .get(calculateRoute + didYouPayAChargePageUrl + period2016Pre + scheme2)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDidYouPayAChargeSecondSchemeConfirmation(value: String): HttpRequestBuilder =
    http("DidYouPayACharge : " + value)
      .post(calculateRoute + didYouPayAChargePageUrl + period2016Pre + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAddAnotherSchemeTwoPage: HttpRequestBuilder =
    http("Navigate to AddAnotherScheme page " + period2016Pre + " Scheme " + scheme2)
      .get(calculateRoute + addAnotherSchemePageUrl + period2016Pre + scheme2)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAddAnotherSchemeTwoConfirmation(value: String): HttpRequestBuilder =
    http("AddAnotherScheme : " + value)
      .post(calculateRoute + addAnotherSchemePageUrl + period2016Pre + scheme2)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToContributedOtherDbDcSchemePage: HttpRequestBuilder =
    http("Navigate to contributedOtherDbDcScheme page " + period2016Pre)
      .get(calculateRoute + contributedOtherDbDcSchemePageUrl + period2016Pre)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContributedOtherDbDcSchemeConfirmation(value: String): HttpRequestBuilder =
    http("contributedOtherDbDcScheme : " + value)
      .post(calculateRoute + contributedOtherDbDcSchemePageUrl + period2016Pre)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhichContributedDuringRemedyPeriodPage: HttpRequestBuilder =
    http("Navigate to whichContributedDuringRemedyPeriodPageUrl page " + period2016Pre)
      .get(calculateRoute + whichContributedDuringRemedyPeriodPageUrl + period2016Pre)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichContributedDuringRemedyPeriodConfirmation(value: String): HttpRequestBuilder =
    http("whichContributedDuringRemedyPeriodPageUrl : " + value)
      .post(calculateRoute + whichContributedDuringRemedyPeriodPageUrl + period2016Pre)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value[1]", value)
      .check(status.is(303))

  val navigateToPiaForDbPensionPage: HttpRequestBuilder =
    http("Navigate to piaForDbPension page " + period2016Pre)
      .get(calculateRoute + piaForDbPensionPageUrl + period2016Pre)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaForDbPensionConfirmation(value: String): HttpRequestBuilder =
    http("piaForDbPensionPageUrl : " + value)
      .post(calculateRoute + piaForDbPensionPageUrl + period2016Pre)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToCheckYourAnswersPeriodPage: HttpRequestBuilder =
    http("Navigate to checkYourAnswersPeriod page " + period2016Pre)
      .get(calculateRoute + checkYourAnswersPeriodPageUrl + period2016Pre)
      .check(status.is(200))
}
