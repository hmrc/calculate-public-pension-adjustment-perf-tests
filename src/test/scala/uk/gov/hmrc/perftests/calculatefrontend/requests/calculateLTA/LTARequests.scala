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

package uk.gov.hmrc.perftests.calculatefrontend.requests.calculateLTA

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration

object LTARequests extends Configuration {
  val calculateRoute: String                                = s"$calculationUrl/public-pension-adjustment"
  val hadBenefitCrystallisationEventPageUrl: String         = "/lifetime-allowance/benefit-crystallisation-event"
  val dateOfBenefitCrystallisationEventPageUrl: String      = "/lifetime-allowance/benefit-crystallisation-event-date"
  val toldChangeInLtaPercentagePageUrl: String              = "/lifetime-allowance/lifetime-allowance-percentage-change"
  val percentageCausedChangeInChargePageUrl: String         = "/lifetime-allowance/lifetime-allowance-charge-change"
  val moreThanOneBenefitCrystallisationEventPageUrl: String =
    "/lifetime-allowance/more-than-one-benefit-crystallisation-event"
  val ltaProtectionOrEnhancementsPageUrl: String            = "/lifetime-allowance/protection-enhancements"
  val enhancementTypePageUrl: String                        = "/lifetime-allowance/enhancement-type"
  val internationalEnhancementReferencePageUrl: String      = "/lifetime-allowance/international-enhancement-reference"
  val pensionCreditReferencePageUrl: String                 = "/lifetime-allowance/pension-credit-reference"
  val protectionEnhancementChangedPageUrl: String           = "/lifetime-allowance/protection-enhancement-changed"
  val protectionChangedNewTypePageUrl: String               = "/lifetime-allowance/new-protection"
  val protectionChangedNewReferencePageUrl: String          = "/lifetime-allowance/new-protection-reference"

  val newEnhancementTypePageUrl: String            = "/lifetime-allowance/new-enhancement-type"
  val newInternationalEnhancementReference: String = "/lifetime-allowance/new-international-enhancement-reference"
  val newPensionCreditReference: String            = "/lifetime-allowance/new-pension-credit-reference"

  val ltaCharge20152023PageUrl: String       = "/lifetime-allowance/charge-2015-2023"
  val howExcessWasPaidPageUrl: String        = "/lifetime-allowance/excess-paid"
  val valueOfLumpSumPageUrl: String          = "/lifetime-allowance/value-of-lump-sum"
  val valueOfAnnualPaymentPageUrl: String    = "/lifetime-allowance/value-of-annual-payment"
  val whoPaidLtaChargePageUrl: String        = "/lifetime-allowance/who-paid-charge"
  val schemePaidLtaChargePageUrl: String     = "/lifetime-allowance/scheme-paid-charge-amount"
  val quarterChargeWasPaidPageUrl: String    = "/lifetime-allowance/quarter-charge-was-paid"
  val yearChargeWasPaidPageUrl: String       = "/lifetime-allowance/year-charge-was-paid"
  val newExcessPaidPageUrl: String           = "/lifetime-allowance/new-excess-paid"
  val newValueOfLumpSumPageUrl: String       = "/lifetime-allowance/new-value-of-lump-sum"
  val newValueOfAnnualPaymentPageUrl: String = "/lifetime-allowance/new-value-of-annual-payment"
  val whoPaidExtraChargePageUrl: String      = "/lifetime-allowance/who-paid-extra-charge"

  val navigateToHadBenefitCrystallisationEventPage: HttpRequestBuilder =
    http("Navigate to benefit-crystallisation-event page ")
      .get(calculateRoute + hadBenefitCrystallisationEventPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHadBenefitCrystallisationEventConfirmation(value: String): HttpRequestBuilder =
    http("benefit-crystallisation-event : " + value)
      .post(calculateRoute + hadBenefitCrystallisationEventPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToDateOfBenefitCrystallisationEventPage: HttpRequestBuilder =
    http("Navigate to benefit-crystallisation-event-date page ")
      .get(calculateRoute + dateOfBenefitCrystallisationEventPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDateOfBenefitCrystallisationEventConfirmation(): HttpRequestBuilder =
    http("benefit-crystallisation-event-date ")
      .post(calculateRoute + dateOfBenefitCrystallisationEventPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", "25")
      .formParam("value.month", "06")
      .formParam("value.year", "2017")
      .check(status.is(303))

  val navigateToToldChangeInLtaPercentagePage: HttpRequestBuilder =
    http("Navigate to lifetime-allowance-percentage-change page ")
      .get(calculateRoute + toldChangeInLtaPercentagePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitToldChangeInLtaPercentageConfirmation(value: String): HttpRequestBuilder =
    http("lifetime-allowance-percentage-change : " + value)
      .post(calculateRoute + toldChangeInLtaPercentagePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPercentageCausedChangeInChargePage: HttpRequestBuilder =
    http("Navigate to lifetime-allowance-charge-change page ")
      .get(calculateRoute + percentageCausedChangeInChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitToPercentageCausedChangeInChargeConfirmation(value: String): HttpRequestBuilder =
    http("lifetime-allowance-charge-change " + value)
      .post(calculateRoute + percentageCausedChangeInChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToMoreThanOneBenefitCrystallisationEventPage: HttpRequestBuilder =
    http("Navigate to more-than-one-benefit-crystallisation-event page ")
      .get(calculateRoute + moreThanOneBenefitCrystallisationEventPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitToMoreThanOneBenefitCrystallisationEventConfirmation(value: String): HttpRequestBuilder =
    http("more-than-one-benefit-crystallisation-event " + value)
      .post(calculateRoute + moreThanOneBenefitCrystallisationEventPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToLtaProtectionOrEnhancementsPage: HttpRequestBuilder =
    http("Navigate to ltaProtectionOrEnhancements page ")
      .get(calculateRoute + ltaProtectionOrEnhancementsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLtaProtectionOrEnhancementsConfirmation(value: String): HttpRequestBuilder =
    http("ltaProtectionOrEnhancements : " + value)
      .post(calculateRoute + ltaProtectionOrEnhancementsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToEnhancementTypePage: HttpRequestBuilder =
    http("Navigate to EnhancementType page ")
      .get(calculateRoute + enhancementTypePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitEnhancementTypePageConfirmation(value: String): HttpRequestBuilder =
    http("EnhancementType : " + value)
      .post(calculateRoute + enhancementTypePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToInternationalEnhancementReferencePage: HttpRequestBuilder =
    http("Navigate to internationalEnhancementReference page ")
      .get(calculateRoute + internationalEnhancementReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitInternationalEnhancementReferencePageConfirmation(value: String): HttpRequestBuilder =
    http("internationalEnhancementReferencePage : " + value)
      .post(calculateRoute + internationalEnhancementReferencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPensionCreditReferencePage: HttpRequestBuilder =
    http("Navigate to PensionCreditReferencePage page ")
      .get(calculateRoute + pensionCreditReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPensionCreditReferencePageConfirmation(value: String): HttpRequestBuilder =
    http("PensionCreditReferencePage : " + value)
      .post(calculateRoute + pensionCreditReferencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectionEnhancementChangedPage: HttpRequestBuilder =
    http("Navigate to protection-enhancement-changed page ")
      .get(calculateRoute + protectionEnhancementChangedPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionEnhancementChangedConfirmation(value: String): HttpRequestBuilder =
    http("protection-enhancement-changed : " + value)
      .post(calculateRoute + protectionEnhancementChangedPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectionChangedNewTypePage: HttpRequestBuilder =
    http("Navigate to protectionChangedNewType page ")
      .get(calculateRoute + protectionChangedNewTypePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionChangedNewTypeConfirmation(value: String): HttpRequestBuilder =
    http("protectionChangedNewType : " + value)
      .post(calculateRoute + protectionChangedNewTypePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectionChangedNewReferencePage: HttpRequestBuilder =
    http("Navigate to protectionChangedNewReference page ")
      .get(calculateRoute + protectionChangedNewReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionChangedNewReferenceConfirmation(value: String): HttpRequestBuilder =
    http("protectionChangedNewReference : " + value)
      .post(calculateRoute + protectionChangedNewReferencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewEnhancementTypePage: HttpRequestBuilder                    =
    http("Navigate to new-enhancement-type page ")
      .get(calculateRoute + newEnhancementTypePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitNewEnhancementTypeConfirmation(value: String): HttpRequestBuilder =
    http("new-enhancement-type : " + value)
      .post(calculateRoute + newEnhancementTypePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewInternationalEnhancementReferencePage: HttpRequestBuilder                    =
    http("Navigate to new-international-enhancement-reference page ")
      .get(calculateRoute + newInternationalEnhancementReference)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitNewInternationalEnhancementReferenceConfirmation(value: String): HttpRequestBuilder =
    http("new-international-enhancement-reference : " + value)
      .post(calculateRoute + newInternationalEnhancementReference)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewPensionCreditReferencePage: HttpRequestBuilder =
    http("Navigate to newPensionCreditReference page ")
      .get(calculateRoute + newPensionCreditReference)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitNewPensionCreditReferenceConfirmation(value: String): HttpRequestBuilder =
    http("newPensionCreditReference : " + value)
      .post(calculateRoute + newPensionCreditReference)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToLtaCharge20152023Page: HttpRequestBuilder =
    http("Navigate to ltaCharge20152023 page ")
      .get(calculateRoute + ltaCharge20152023PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLtaCharge20152023Confirmation(value: String): HttpRequestBuilder =
    http("ltaCharge20152023 : " + value)
      .post(calculateRoute + ltaCharge20152023PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHowExcessWasPaidPage: HttpRequestBuilder =
    http("Navigate to howExcessWasPaid page ")
      .get(calculateRoute + howExcessWasPaidPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowExcessWasPaidConfirmation(value: String): HttpRequestBuilder =
    http("howExcessWasPaid : " + value)
      .post(calculateRoute + howExcessWasPaidPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToValueOfLumpSumPage: HttpRequestBuilder =
    http("Navigate to valueOfLumpSumPage page ")
      .get(calculateRoute + valueOfLumpSumPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitValueOfLumpSumPageConfirmation(value: String): HttpRequestBuilder =
    http("valueOfLumpSumPage : " + value)
      .post(calculateRoute + valueOfLumpSumPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToValueOfAnnualPaymentPage: HttpRequestBuilder =
    http("Navigate to valueOfAnnualPayment page ")
      .get(calculateRoute + valueOfAnnualPaymentPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitValueOfAnnualPaymentPageUrlConfirmation(value: String): HttpRequestBuilder =
    http("valueOfAnnualPaymentPage : " + value)
      .post(calculateRoute + valueOfAnnualPaymentPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhoPaidLtaChargePage: HttpRequestBuilder =
    http("Navigate to whoPaidLtaCharge page ")
      .get(calculateRoute + whoPaidLtaChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhoPaidLtaChargeConfirmation(value: String): HttpRequestBuilder =
    http("whoPaidLtaCharge : " + value)
      .post(calculateRoute + whoPaidLtaChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToSchemePaidLtaChargePage: HttpRequestBuilder =
    http("Navigate to scheme-paid-charge-amount page ")
      .get(calculateRoute + schemePaidLtaChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSchemePaidLtaChargePageUrlConfirmation(value: String, value2: String): HttpRequestBuilder =
    http("scheme-paid-charge-amount : " + value)
      .post(calculateRoute + schemePaidLtaChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("name", value)
      .formParam("taxRef", value2)
      .check(status.is(303))

  val navigateToQuarterChargeWasPaidPage: HttpRequestBuilder =
    http("Navigate to quarterChargeWasPaid page ")
      .get(calculateRoute + quarterChargeWasPaidPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitQuarterChargeWasPaidPageConfirmation(value: String): HttpRequestBuilder =
    http("quarterChargeWasPaidPage " + value)
      .post(calculateRoute + quarterChargeWasPaidPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToYearChargeWasPaidPage: HttpRequestBuilder =
    http("Navigate to yearChargeWasPaid page ")
      .get(calculateRoute + yearChargeWasPaidPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitYearChargeWasPaidConfirmation(value: String): HttpRequestBuilder =
    http("yearChargeWasPaid " + value)
      .post(calculateRoute + yearChargeWasPaidPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewExcessPaidPage: HttpRequestBuilder =
    http("Navigate to newExcessPaid page ")
      .get(calculateRoute + newExcessPaidPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitNewExcessPaidPageConfirmation(value: String): HttpRequestBuilder =
    http("newExcessPaidPage " + value)
      .post(calculateRoute + newExcessPaidPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewValueOfLumpSumPage: HttpRequestBuilder                        =
    http("Navigate to newValueOfLumpSumPage page ")
      .get(calculateRoute + newValueOfLumpSumPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)
  def submitNewValueOfLumpSumPageConfirmation(value: String): HttpRequestBuilder =
    http("newValueOfLumpSumPage " + value)
      .post(calculateRoute + newValueOfLumpSumPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToNewValueOfAnnualPaymentPage: HttpRequestBuilder =
    http("Navigate to newValueOfAnnualPayment page ")
      .get(calculateRoute + newValueOfAnnualPaymentPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitNewValueOfAnnualPaymentPageConfirmation(value: String): HttpRequestBuilder =
    http("newValueOfAnnualPaymentPage " + value)
      .post(calculateRoute + newValueOfAnnualPaymentPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToWhoPaidExtraChargePage: HttpRequestBuilder =
    http("Navigate to whoPaidExtraChargePage page ")
      .get(calculateRoute + whoPaidExtraChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitWhoPaidExtraChargePageConfirmation(value: String): HttpRequestBuilder =
    http("whoPaidExtraChargePage " + value)
      .post(calculateRoute + whoPaidExtraChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))
}
