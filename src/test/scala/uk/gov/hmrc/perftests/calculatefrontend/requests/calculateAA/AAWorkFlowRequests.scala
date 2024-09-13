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

object AAWorkFlowRequests extends Configuration {

  val calculateRoute: String                               = s"$calculationUrl/public-pension-adjustment"
  val scottishTaxpayerFrom2016PageUrl: String              = "/annual-allowance/scottish-taxpayer"
  val whichYearsScottishTaxpayerPageUrl: String            = "/annual-allowance/scottish-taxpayer-years"
  val payingIntoPublicPensionSchemePageUrl: String         = "/annual-allowance/paying-into-public-service-pension"
  val whenStopPayingIntoPublicPensionSchemePageUrl: String =
    "/annual-allowance/stopped-paying-into-public-service-pension"
  val haveDefinedContributionPensionPageUrl: String        = "/annual-allowance/defined-contributions-scheme"
  val haveFlexiblyAccessedPensionPageUrl: String           = "/annual-allowance/flexibly-accessed"
  val payTaxChargeFrom20152016PageUrl: String              = "/annual-allowance/tax-charge-between-2015-2016"
  val piaPreRemedy2013PageUrl: String                      = "/annual-allowance/pension-input-amount/2013"
  val piaPreRemedy2014PageUrl: String                      = "/annual-allowance/pension-input-amount/2014"
  val piaPreRemedy2015PageUrl: String                      = "/annual-allowance/pension-input-amount/2015"
  val checkYourAnswersAnnualAllowanceSetupPageUrl: String  = "/annual-allowance/setup-check-answers"
  val taskListPageUrl: String                              = "/task-list"
  val calculationResultPageUrl: String                     = "/calculation-result"
  val payTaxChargeFrom20102011PageUrl: String              = "/annual-allowance/registered/2011"
  val payTaxChargeFrom20112012PageUrl: String              = "/annual-allowance/registered/2012"
  val payTaxChargeFrom20122013PageUrl: String              = "/annual-allowance/registered/2013"
  val payTaxChargeFrom20132014PageUrl: String              = "/annual-allowance/registered/2014"
  val payTaxChargeFrom20142015PageUrl: String              = "/annual-allowance/registered/2015"
  val payTaxCharge20142015PageUrl: String                  = "/annual-allowance/tax-charge-2014-2015"
  val piaPreRemedy2011PageUrl: String                      = "/annual-allowance/pension-input-amount/2011"
  val piaPreRemedy2012PageUrl: String                      = "/annual-allowance/pension-input-amount/2012"

  val navigateToScottishTaxpayerFrom2016Page: HttpRequestBuilder =
    http("Navigate to ScottishTaxpayerFrom2016 page")
      .get(calculateRoute + scottishTaxpayerFrom2016PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitScottishTaxpayerFrom2016Confirmation(value: String): HttpRequestBuilder =
    http("scottishTaxpayerFrom2016 : " + value)
      .post(calculateRoute + scottishTaxpayerFrom2016PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhichYearsScottishTaxpayerPage: HttpRequestBuilder =
    http("Navigate to whichYearsScottishTaxpayerPageUrl page")
      .get(calculateRoute + whichYearsScottishTaxpayerPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhichYearsScottishTaxpayerPageConfirmation(): HttpRequestBuilder =
    http("whichYearsScottishTaxpayerPage : ")
      .post(calculateRoute + whichYearsScottishTaxpayerPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value[2]", "2021")
      .formParam("value[4]", "2019")
      .formParam("value[6]", "2017")
      .check(status.is(303))

  val navigateToPayingIntoPublicPensionSchemePageUrlPage: HttpRequestBuilder =
    http("Navigate to payingIntoPublicPensionSchemePageUrl page")
      .get(calculateRoute + payingIntoPublicPensionSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayingIntoPublicPensionSchemePageUrlPageConfirmation(value: String): HttpRequestBuilder =
    http("payingIntoPublicPensionSchemePageUrl : " + value)
      .post(calculateRoute + payingIntoPublicPensionSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateWhenStopPayingIntoPublicPensionSchemePageUrlPage: HttpRequestBuilder =
    http("Navigate to stopPayingIntoPublicPensionSchemePageUrl page")
      .get(calculateRoute + whenStopPayingIntoPublicPensionSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhenStopPayingIntoPublicPensionSchemePageUrlPageConfirmation(
    day: Int,
    month: Int,
    year: Int
  ): HttpRequestBuilder =
    http(s"stopPayingIntoPublicPensionSchemePageUrl : $day - $month - $year")
      .post(calculateRoute + whenStopPayingIntoPublicPensionSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", day)
      .formParam("value.month", month)
      .formParam("value.year", year)
      .check(status.is(303))

  val navigateToHaveDefinedContributionPensionPageUrlPage: HttpRequestBuilder =
    http("Navigate to haveDefinedContributionPension page")
      .get(calculateRoute + haveDefinedContributionPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHaveDefinedContributionPensionPageConfirmation(value: String): HttpRequestBuilder =
    http("haveDefinedContributionPension : " + value)
      .post(calculateRoute + haveDefinedContributionPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHaveFlexiblyAccessedPensionUrlPage: HttpRequestBuilder =
    http("Navigate to haveFlexiblyAccessedPension page")
      .get(calculateRoute + haveFlexiblyAccessedPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHaveFlexiblyAccessedPensionPageConfirmation(value: String): HttpRequestBuilder =
    http("haveFlexiblyAccessedPension : " + value)
      .post(calculateRoute + haveFlexiblyAccessedPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20152016UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20152016 page")
      .get(calculateRoute + payTaxChargeFrom20152016PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20152016Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20152016 : " + value)
      .post(calculateRoute + payTaxChargeFrom20152016PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPiaPreRemedy2013Page: HttpRequestBuilder =
    http("Navigate to piaPreRemedy2013 page")
      .get(calculateRoute + piaPreRemedy2013PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaPreRemedy2013PageConfirmation(value: String): HttpRequestBuilder =
    http("piaPreRemedy2013 : " + value)
      .post(calculateRoute + piaPreRemedy2013PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPiaPreRemedy2014Page: HttpRequestBuilder =
    http("Navigate to piaPreRemedy2014 page")
      .get(calculateRoute + piaPreRemedy2014PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaPreRemedy2014PageConfirmation(value: String): HttpRequestBuilder =
    http("piaPreRemedy2014 : " + value)
      .post(calculateRoute + piaPreRemedy2014PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPiaPreRemedy2015Page: HttpRequestBuilder =
    http("Navigate to piaPreRemedy2015 page")
      .get(calculateRoute + piaPreRemedy2015PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaPreRemedy2015PageConfirmation(value: String): HttpRequestBuilder =
    http("piaPreRemedy2015 : " + value)
      .post(calculateRoute + piaPreRemedy2015PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToCheckYourAnswersAnnualAllowanceSetupPage: HttpRequestBuilder =
    http("Navigate to checkYourAnswersAnnualAllowanceSetup page")
      .get(calculateRoute + checkYourAnswersAnnualAllowanceSetupPageUrl)
      .check(status.is(200))

  val navigateToTaskListPageUrlPage: HttpRequestBuilder =
    http("Navigate to taskListPageUrl page")
      .get(calculateRoute + taskListPageUrl)
      .check(status.is(200))

  val navigateToCalculationResultPageUrlPage: HttpRequestBuilder =
    http("Navigate to calculationResultPageUrl page")
      .get(calculateRoute + calculationResultPageUrl)
      .check(status.is(200))

  val navigateToPayTaxCharge20142015UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeBetween20142015 page")
      .get(calculateRoute + payTaxCharge20142015PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxCharge20142015Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeBetween20142015 : " + value)
      .post(calculateRoute + payTaxCharge20142015PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20142015UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20142015 page")
      .get(calculateRoute + payTaxChargeFrom20142015PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20142015Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20142015 : " + value)
      .post(calculateRoute + payTaxChargeFrom20142015PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20132014UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20132014 page")
      .get(calculateRoute + payTaxChargeFrom20132014PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20132014Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20132014 : " + value)
      .post(calculateRoute + payTaxChargeFrom20132014PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20122013UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20122013 page")
      .get(calculateRoute + payTaxChargeFrom20122013PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20122013Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20122013 : " + value)
      .post(calculateRoute + payTaxChargeFrom20122013PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20112012UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20112012 page")
      .get(calculateRoute + payTaxChargeFrom20112012PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20112012Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20112012 : " + value)
      .post(calculateRoute + payTaxChargeFrom20112012PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPayTaxChargeFrom20102011UrlPage: HttpRequestBuilder =
    http("Navigate to payTaxChargeFrom20102011 page")
      .get(calculateRoute + payTaxChargeFrom20102011PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPayTaxChargeFrom20102011Confirmation(value: String): HttpRequestBuilder =
    http("payTaxChargeFrom20102011 : " + value)
      .post(calculateRoute + payTaxChargeFrom20102011PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPiaPreRemedy2011Page: HttpRequestBuilder =
    http("Navigate to piaPreRemedy2011 page")
      .get(calculateRoute + piaPreRemedy2011PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaPreRemedy2011PageConfirmation(value: String): HttpRequestBuilder =
    http("piaPreRemedy2011 : " + value)
      .post(calculateRoute + piaPreRemedy2011PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPiaPreRemedy2012Page: HttpRequestBuilder =
    http("Navigate to piaPreRemedy2012 page")
      .get(calculateRoute + piaPreRemedy2012PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPiaPreRemedy2012PageConfirmation(value: String): HttpRequestBuilder =
    http("piaPreRemedy2012 : " + value)
      .post(calculateRoute + piaPreRemedy2012PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

}
