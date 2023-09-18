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
  val calculateRoute: String                           = s"$calculationUrl/public-pension-adjustment"
  val hadBenefitCrystallisationEventPageUrl: String    = "/had-benefit-crystallisation-event"
  val dateOfBenefitCrystallisationEventPageUrl: String = "/date-of-benefit-crystallisation-event"
  val toldChangeInLtaPercentagePageUrl: String         = "/told-change-in-lta-percentage"
  val percentageCausedChangeInChargePageUrl: String    = "/percentage-caused-change-in-charge"
  val ltaProtectionOrEnhancementsPageUrl: String       = "/lta-protection-or-enhancements"
  val protectionTypePageUrl: String                    = "/protection-type"
  val protectionReferencePageUrl: String               = "/protection-reference"
  val protectionChangedPageUrl: String                 = "/protection-changed"
  val protectionChangedNewTypePageUrl: String          = "/protection-changed-new-type"
  val protectionChangedNewReferencePageUrl: String     = "/protection-changed-new-reference"
  val ltaCharge20152023PageUrl: String                 = "/lta-charge-2015-2023"
  val howExcessWasPaidPageUrl: String                  = "/how-excess-was-paid"
  val howMuchLtaChargePageUrl: String                  = "/how-much-lta-charge"
  val whoPaidLtaChargePageUrl: String                  = "/who-paid-lta-charge"
  val valueNewLtaChargePageUrl: String                 = "/value-new-lta-charge"

  val navigateToHadBenefitCrystallisationEventPage: HttpRequestBuilder =
    http("Navigate to hadBenefitCrystallisationEvent page ")
      .get(calculateRoute + hadBenefitCrystallisationEventPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHadBenefitCrystallisationEventConfirmation(value: String): HttpRequestBuilder =
    http("hadBenefitCrystallisationEvent : " + value)
      .post(calculateRoute + hadBenefitCrystallisationEventPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToDateOfBenefitCrystallisationEventPage: HttpRequestBuilder =
    http("Navigate to dateOfBenefitCrystallisationEvent page ")
      .get(calculateRoute + dateOfBenefitCrystallisationEventPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDateOfBenefitCrystallisationEventConfirmation(): HttpRequestBuilder =
    http("dateOfBenefitCrystallisationEventPage : ")
      .post(calculateRoute + dateOfBenefitCrystallisationEventPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value.day", "25")
      .formParam("value.month", "06")
      .formParam("value.year", "2017")
      .check(status.is(303))

  val navigateToToldChangeInLtaPercentagePage: HttpRequestBuilder =
    http("Navigate to toldChangeInLtaPercentage page ")
      .get(calculateRoute + toldChangeInLtaPercentagePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitToldChangeInLtaPercentageConfirmation(value: String): HttpRequestBuilder =
    http("toldChangeInLtaPercentage : " + value)
      .post(calculateRoute + toldChangeInLtaPercentagePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPercentageCausedChangeInChargePage: HttpRequestBuilder =
    http("Navigate to percentageCausedChangeInCharge page ")
      .get(calculateRoute + percentageCausedChangeInChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitToPercentageCausedChangeInChargeConfirmation(value: String): HttpRequestBuilder =
    http("percentageCausedChangeInCharge : " + value)
      .post(calculateRoute + percentageCausedChangeInChargePageUrl)
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

  val navigateToProtectionTypePage: HttpRequestBuilder =
    http("Navigate to protectionTypePage page ")
      .get(calculateRoute + protectionTypePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionTypePageConfirmation(value: String): HttpRequestBuilder =
    http("protectionTypePage : " + value)
      .post(calculateRoute + protectionTypePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectionReferencePage: HttpRequestBuilder =
    http("Navigate to percentageCausedChangeInCharge page ")
      .get(calculateRoute + protectionReferencePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionReferenceConfirmation(value: String): HttpRequestBuilder =
    http("percentageCausedChangeInCharge : " + value)
      .post(calculateRoute + protectionReferencePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToProtectionChangedPage: HttpRequestBuilder =
    http("Navigate to protectionChanged page ")
      .get(calculateRoute + protectionChangedPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitProtectionChangedConfirmation(value: String): HttpRequestBuilder =
    http("protectionChangedPage : " + value)
      .post(calculateRoute + protectionChangedPageUrl)
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

  val navigateToHowMuchLtaChargePage: HttpRequestBuilder =
    http("Navigate to howMuchLtaCharge page ")
      .get(calculateRoute + howMuchLtaChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowMuchLtaChargeConfirmation(value: String): HttpRequestBuilder =
    http("howMuchLtaCharge : " + value)
      .post(calculateRoute + howMuchLtaChargePageUrl)
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

  val navigateToValueNewLtaChargePage: HttpRequestBuilder =
    http("Navigate to valueNewLtaCharge page ")
      .get(calculateRoute + valueNewLtaChargePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitValueNewLtaChargeConfirmation(value: String): HttpRequestBuilder =
    http("valueNewLtaCharge : " + value)
      .post(calculateRoute + valueNewLtaChargePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))
}
