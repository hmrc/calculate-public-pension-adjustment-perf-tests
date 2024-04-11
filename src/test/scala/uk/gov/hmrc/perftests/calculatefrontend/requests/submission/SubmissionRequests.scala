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

package uk.gov.hmrc.perftests.calculatefrontend.requests.submission

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration

import scala.io.Source

object SubmissionRequests extends Configuration {
  val expectedDeclaration                            =
    "p:contains('on behalf of ABC BCDEFGHIJK')"
  val expectedPensionSchemeReference                 = "dd:contains('TAX00000629RTED')"
  val submissionConfirmation                         = "h1:contains('Adjustment sent')"
  val submitRoute: String                            = s"$submissionFrontendUrl/submit-public-pension-adjustment/submission-service"
  val submitRouteNoService: String                   = s"$submissionFrontendUrl/submit-public-pension-adjustment"
  val finalSubmitRoute: String                       = s"$finalSubmissionBackendUrl/submit-public-pension-adjustment"
  val claimOnBehalfPageUrl: String                   = "/submitting-on-behalf-someone-else"
  val statusOfUserPageUrl: String                    = "/authority-someone-else"
  val theirNamePageUrl: String                       = "/name-someone-else"
  val theirDobPageUrl: String                        = "/date-of-birth-someone-else"
  val theirDateOfDeathPageUrl: String                = "/date-of-death-someone-else"
  val theirNinoPageUrl: String                       = "/national-insurance-number-someone-else"
  val theirUTRPageUrl: String                        = "/unique-taxpayer-reference-someone-else "
  val theirResidencePageUrl: String                  = "/uk-resident-someone-else"
  val theirUKAddressPageUrl: String                  = "/address-someone-else"
  val alternativeNamePageUrl: String                 = "/name-pension-scheme-holds"
  val enterAlternativeNamePageUrl: String            = "/enter-name-pension-scheme-holds"
  val contactNumberPageUrl: String                   = "/contact-number"
  val residencePageUrl: String                       = "/uk-resident"
  val internationalAddressPageUrl: String            = "/your-international-address"
  val legacyPensionSchemeReferencePageUrl: String    = "/legacy-individual-pension-scheme-reference"
  val reformPensionSchemeReferencePageUrl: String    = "/reform-individual-pension-scheme-reference"
  val claimingAdditionalTaxRateReliefPageUrl: String = "/claiming-higher-additional-tax-rate-relief"
  val taxReliefAmountPageUrl: String                 = "/how-much-tax-relief-claiming-for"
  val whichPensionSchemeWillPayTaxRelief: String     = "/which-pension-scheme-will-pay-tax-relief"
  val bankDetailsPageUrl                             = "/bank-details"
  val declarationsPageUrl                            = "/declarations"
  val checkYourAnswersSubmitPageUrl                  = "/check-your-answers"
  val submitYourAnswersSubmitPageUrl                 = "/final-submission"
  val submissionPageUrl                              = "/submission"
  val jsonPayload                                    = Source.fromResource("data/submitFrontend.json").getLines().mkString

  val sessionHeaders =
    Map(
      "Authorization" -> "Bearer ${bearerToken}",
      "Content-Type"  -> "application/json"
    )

  val navigateToClaimOnBehalfPage: HttpRequestBuilder                           =
    http("Navigate to claimOnBehalfPage")
      .get(submitRoute + claimOnBehalfPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitClaimOnBehalfPageUrlConfirmation(value: String): HttpRequestBuilder =
    http("submit to claimOnBehalfPage : " + value)
      .post(submitRoute + claimOnBehalfPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToStatusOfUserPage: HttpRequestBuilder                        =
    http("Navigate to statusOfUserPage")
      .get(submitRoute + statusOfUserPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitStatusOfUserPageConfirmation(value: String): HttpRequestBuilder =
    http("submit to statusOfUserPage : " + value)
      .post(submitRoute + statusOfUserPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTheirNamePage: HttpRequestBuilder =
    http("Navigate to theirNamePage")
      .get(submitRoute + theirNamePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTheirNamePageConfirmation(value: String): HttpRequestBuilder =
    http("submit to theirNamePage : " + value)
      .post(submitRoute + theirNamePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTheirDobPage: HttpRequestBuilder           =
    http("Navigate to theirDobPage")
      .get(submitRoute + theirDobPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirDobPageConfirmation(): HttpRequestBuilder =
    http("submit to theirDobPage : ")
      .post(submitRoute + theirDobPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", "28")
      .formParam("value.month", "11")
      .formParam("value.year", "1964")
      .check(status.is(303))

  val navigateToTheirDateOfDeathPage: HttpRequestBuilder           =
    http("Navigate to theirDateOfDeathPage")
      .get(submitRoute + theirDateOfDeathPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirDateOfDeathPageConfirmation(): HttpRequestBuilder =
    http("submit to theirDateOfDeathPage : ")
      .post(submitRoute + theirDateOfDeathPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", "16")
      .formParam("value.month", "9")
      .formParam("value.year", "2023")
      .check(status.is(303))

  val navigateToTheirNinoPage: HttpRequestBuilder                           =
    http("Navigate to theirNinoPage")
      .get(submitRoute + theirNinoPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirNinoPageUrlConfirmation(value: String): HttpRequestBuilder =
    http("submit to theirNinoPage : ")
      .post(submitRoute + theirNinoPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTheirUTRPage: HttpRequestBuilder        =
    http("Navigate to TheirUTRP")
      .get(submitRoute + theirUTRPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirUTRPConfirmation(): HttpRequestBuilder =
    http("submit to TheirUTRP : ")
      .post(submitRoute + theirUTRPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "1234567891")
      .check(status.is(303))

  val navigateToTheirResidencePage: HttpRequestBuilder                        =
    http("Navigate to TheirResidence")
      .get(submitRoute + theirResidencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirResidencePageConfirmation(value: String): HttpRequestBuilder =
    http("submit to TheirResidence : ")
      .post(submitRoute + theirResidencePageUrl)
      .formParam("value", value)
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToTheirUKAddressPage: HttpRequestBuilder           =
    http("Navigate to theirUKAddressPage")
      .get(submitRoute + theirUKAddressPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitTheirUKAddressPageConfirmation(): HttpRequestBuilder =
    http("submit to theirUKAddressPage ")
      .post(submitRoute + theirUKAddressPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("addressLine1", "No 134")
      .formParam("addressLine2", "Prince abc road")
      .formParam("townOrCity", "London")
      .formParam("county", "London")
      .formParam("postCode", "AB2 5ED")
      .check(status.is(303))

  val navigateToAlternativeNamePage: HttpRequestBuilder                        =
    http("Navigate to alternativeNamePage")
      .get(submitRoute + alternativeNamePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitAlternativeNamePageConfirmation(value: String): HttpRequestBuilder =
    http("submit to alternativeNamePage ")
      .post(submitRoute + alternativeNamePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToEnterAlternativeNamePage: HttpRequestBuilder =
    http("Navigate to enterAlternativeNamePage")
      .get(submitRoute + enterAlternativeNamePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEnterAlternativeNamePageConfirmation(value: String): HttpRequestBuilder =
    http("submit to enterAlternativeNamePage ")
      .post(submitRoute + enterAlternativeNamePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToContactNumberPage: HttpRequestBuilder =
    http("Navigate to contactNumberPageUrl")
      .get(submitRoute + contactNumberPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContactNumberPageConfirmation(): HttpRequestBuilder =
    http("submit to contactNumberPage ")
      .post(submitRoute + contactNumberPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "+44 808 157 0192")
      .check(status.is(303))

  val navigateToResidencePage: HttpRequestBuilder =
    http("Navigate to residencePage")
      .get(submitRoute + residencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitResidencePagConfirmation(value: String): HttpRequestBuilder =
    http("submit to residencePage ")
      .post(submitRoute + residencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToInternationalAddressPagePage: HttpRequestBuilder =
    http("Navigate to internationalAddressPage")
      .get(submitRoute + internationalAddressPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitInternationalAddressPageConfirmation(): HttpRequestBuilder =
    http("submit to internationalAddressPage ")
      .post(submitRoute + internationalAddressPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("addressLine1", "No 138")
      .formParam("addressLine2", "Prince ref road")
      .formParam("townOrCity", "London")
      .formParam("stateOrRegion", "London")
      .formParam("postCode", "AB1 9ED")
      .formParam("country", "France")
      .check(status.is(303))

  val navigateToLegacyPensionSchemeReferencePage: HttpRequestBuilder =
    http("Navigate to legacyPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RU" + legacyPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLegacyPensionSchemeReferencePageConfirmation(): HttpRequestBuilder =
    http("submit to legacyPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RU" + legacyPensionSchemeReferencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", "00348916RU")
      .check(status.is(303))

  val navigateToReformPensionSchemeReferencePageUrlPage: HttpRequestBuilder =
    http("Navigate to reformPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RU" + reformPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReformPensionSchemeReferencePageUrlConfirmation(): HttpRequestBuilder =
    http("submit to reformPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RU" + reformPensionSchemeReferencePageUrl)
      .formParam("value", "TAX00000629RTED")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToLegacyPensionScheme2ReferencePage: HttpRequestBuilder =
    http("Navigate to legacyPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RG" + legacyPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLegacyPensionScheme2ReferencePageConfirmation(): HttpRequestBuilder =
    http("submit to legacyPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RG" + legacyPensionSchemeReferencePageUrl)
      .formParam("value", "00348916RG")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToReformPensionScheme2ReferencePageUrlPage: HttpRequestBuilder =
    http("Navigate to reformPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RG" + reformPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReformPensionScheme2ReferencePageUrlConfirmation(): HttpRequestBuilder =
    http("submit to reformPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RG" + reformPensionSchemeReferencePageUrl)
      .formParam("value", "TAX00000629RTED")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToLegacyPensionScheme3ReferencePage: HttpRequestBuilder =
    http("Navigate to legacyPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RX" + legacyPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLegacyPensionScheme3ReferencePageConfirmation(): HttpRequestBuilder =
    http("submit to legacyPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RX" + legacyPensionSchemeReferencePageUrl)
      .formParam("value", "00348916RX")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToReformPensionScheme3ReferencePageUrlPage: HttpRequestBuilder =
    http("Navigate to reformPensionSchemeReferencePage")
      .get(submitRoute + "/00348916RX" + reformPensionSchemeReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitReformPensionScheme3ReferencePageUrlConfirmation(): HttpRequestBuilder =
    http("submit to reformPensionSchemeReferencePage ")
      .post(submitRoute + "/00348916RX" + reformPensionSchemeReferencePageUrl)
      .formParam("value", "TAX00000629RTED")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToClaimingAdditionalTaxRateReliefPageUrlPage: HttpRequestBuilder =
    http("Navigate to claimingAdditionalTaxRateReliefPage")
      .get(submitRoute + claimingAdditionalTaxRateReliefPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitClaimingAdditionalTaxRateReliefPageUrlConfirmation(): HttpRequestBuilder =
    http("submit to claimingAdditionalTaxRateReliefPage ")
      .post(submitRoute + claimingAdditionalTaxRateReliefPageUrl)
      .formParam("value", "true")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToTaxReliefAmountPage: HttpRequestBuilder =
    http("Navigate to TaxReliefAmount")
      .get(submitRoute + taxReliefAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTaxReliefAmountPageUrlConfirmation(): HttpRequestBuilder =
    http("submit to TaxReliefAmount ")
      .post(submitRoute + taxReliefAmountPageUrl)
      .formParam("value", "101010")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToWhichPensionSchemeWillPayPage: HttpRequestBuilder              =
    http("Navigate to whichPensionSchemeWillPay")
      .get(submitRoute + whichPensionSchemeWillPayTaxRelief)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitWhichPensionSchemeWillPayPageUrlConfirmation(): HttpRequestBuilder =
    http("submit to whichPensionSchemeWillPay ")
      .post(submitRoute + whichPensionSchemeWillPayTaxRelief)
      .formParam("value", "Scheme 2 / 00348916RG")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToBankDetailsPage: HttpRequestBuilder =
    http("Navigate to whichPensionSchemeWillPay")
      .get(submitRouteNoService + bankDetailsPageUrl)
      .check(status.is(200))
      .formParam("csrfToken", "${csrfToken}")
      .check(saveCsrfToken)

  def submitBankDetailsPageConfirmation(): HttpRequestBuilder =
    http("submit to bankDetailsPageUrl ")
      .post(submitRouteNoService + bankDetailsPageUrl)
      .formParam("accountName", "Teddy Dickson")
      .formParam("sortCode", "207102")
      .formParam("accountNumber", "44311655")
      .formParam("csrfToken", "${csrfToken}")
      .check(status.is(303))

  val navigateToDeclarationsPage: HttpRequestBuilder =
    http("Navigate to declarationsPage")
      .get(submitRouteNoService + declarationsPageUrl)
      .check(status.is(200))
      .check(css(expectedDeclaration).find.exists)

  val navigateToCheckYourAnswersSubmitPage: HttpRequestBuilder =
    http("Navigate to checkYourAnswersSubmitPage")
      .get(submitRouteNoService + checkYourAnswersSubmitPageUrl)
      .check(status.is(200))

  def submitCheckYourAnswersSubmitPageConfirmation(): HttpRequestBuilder =
    http("Final Submission")
      .post(finalSubmitRoute + submitYourAnswersSubmitPageUrl)
      .headers(sessionHeaders)
      .body(StringBody(jsonPayload))
      .check(status.is(200))
}
