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

  val period                                                = s"/$periodYear"
  val calculateRoute: String                                = s"$calculationUrl/public-pension-adjustment/annual-allowance"
  val scheme1: String                                       = "0"
  val scheme2: String                                       = "1"
  val scheme3: String                                       = "2"
  val whatYouWillNeedAaPageUrl: String                      = "/information"
  val memberMoreThanOnePensionPageUrl: String               = "/multiple-schemes"
  val pensionSchemeDetailsPageUrl: String                   = "/scheme-name-reference"
  val pensionSchemeInputAmountsPageUrl: String              = "/pension-input-amount"
  val didYouPayAChargePageUrl: String                       = "/annual-allowance-charge"
  val addAnotherSchemePageUrl: String                       = "/pension-scheme-summary"
  val contributedOtherDbDcSchemePageUrl: String             = "/contributed-to-any-other-dc-or-db-scheme"
  val whichContributedDuringRemedyPeriodPageUrl: String     = "/contributed-to-dc-or-db-scheme"
  val piaForDbPensionPageUrl: String                        = "/pension-input-amount-defined-benefit"
  val checkYourAnswersPeriodPageUrl: String                 = "/check-answers"
  val whoPaidChargePageUrl: String                          = "/who-paid-charge"
  val howMuchChargePageUrl: String                          = "/charge-amount-you-paid"
  val whichSchemeDetailsPageUrl: String                     = "/select-scheme"
  val totalIncomePageUrl: String                            = "/total-income"
  val taxableIncomePageUrl: String                          = "/taxable-income"
  val claimingTaxReliefPageUrl: String                      = "/claiming-tax-relief"
  val taxReliefPageUrl: String                              = "/tax-relief"
  val contributeToReliefAtSourceSchemePageUrl: String       = "/contribute-to-relief-at-source-scheme"
  val howMuchContributionReliefAtSourcePageUrl: String      = "/how-much-contribution-relief-at-source"
  val donateViaGiftAidPageUrl: String                       = "/donated-via-gift-aid"
  val donateViaGiftAidAmountPageUrl: String                 = "/donated-via-gift-aid-amount"
  val doYouKnowPersonalAllowancePageUrl: String             = "/do-you-know-personal-allowance"
  val personalAllowanceAmountPageUrl: String                = "/personal-allowance-amount"
  val blindPersonAllowancePageUrl: String                   = "/blind-person-allowance"
  val blindPersonAllowanceAmountPageUrl: String             = "/blind-persons-allowance-amount"
  val thresholdIncomePageUrl: String                        = "/threshold-income"
  val pensionSchemeInputAmount1516period1PageUrl: String    = "/pension-input-amount-15-16-period-1"
  val pensionSchemeInputAmount1516period2PageUrl: String    = "/pension-input-amount-15-16-period-2"
  val prePiaForDbPension2016PageUrl: String                 = "/2016pre-pension-input-amount-defined-benefit"
  val postPiaForDbPension2016PageUrl: String                = "/2016post-pension-input-amount-defined-benefit"
  val anySalarySacrificeArrangementsPageUrl: String         = "/any-salary-sacrifice-arrangements"
  val amountSalarySacrificeArrangementsPageUrl: String      = "/amount-salary-sacrifice-arrangements"
  val flexibleRemunerationArrangementsPageUrl: String       = "/flexible-remuneration-arrangements"
  val amountFlexibleRemunerationArrangementsPageUrl: String = "/amount-flexible-remuneration-arrangements"
  val anyLumpSumDeathBenefitsPageUrl: String                = "/any-lump-sum-death-benefits"
  val lumpSumDeathBenefitsValuePageUrl: String              = "/lump-sum-death-benefits-value"
  val knowAdjustedAmountPageUrl: String                     = "/know-adjusted-amount"
  val adjustedIncomePageUrl: String                         = "/adjusted-income"
  val claimingTaxReliefPensionPageUrl: String               = "/claiming-tax-relief-pension"
  val howMuchTaxReliefPensionPageUrl: String                = "/how-much-tax-relief-pension"
  val howMuchContributionPageUrl: String                    = "/how-much-contribution"
  val anyTaxReliefOverSeasPensionPageUrl: String            = "/any-tax-relief-overseas-pension"
  val taxReliefOverseasPensionValuePageUrl: String          = "/tax-relief-overseas-pension-value"
  val tradeUnionReliefPageUrl: String                       = "/trade-union-relief"
  val unionPoliceReliefAmountPageUrl: String                = "/union-police-relief-amount"

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

  val navigateToFirstPensionSchemeInputAmount1516period1UrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmount1516period1PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstPensionSchemeInputAmount1516period1(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmount1516period1PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToFirstPensionSchemeInputAmount1516period2UrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmount1516period2PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFirstPensionSchemeInputAmount1516period2(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme1 + pensionSchemeInputAmount1516period2PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToSecondPensionSchemeInputAmount1516period1UrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmount1516period1PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondPensionSchemeInputAmount1516period1(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmount1516period1PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
      .check(status.is(303))

  val navigateToSecondPensionSchemeInputAmount1516period2UrlPage: HttpRequestBuilder =
    http("Navigate to pensionSchemeInputAmounts page " + period + " Scheme " + scheme1)
      .get(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmount1516period2PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitSecondPensionSchemeInputAmount1516period2(
    revisedPIA: String
  ): HttpRequestBuilder =
    http("pensionSchemeInputAmounts : " + revisedPIA)
      .post(calculateRoute + period + "/pension-scheme-" + scheme2 + pensionSchemeInputAmount1516period2PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("revisedPIA", revisedPIA)
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

  val navigateToPrePiaForDb2016PensionPage: HttpRequestBuilder =
    http("Navigate to piaForDbPension page " + period)
      .get(calculateRoute + prePiaForDbPension2016PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPrePiaForDbPension2016Confirmation(value: String): HttpRequestBuilder =
    http("piaForDbPensionPageUrl : " + value)
      .post(calculateRoute + prePiaForDbPension2016PageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPostPiaForDb2016PensionPage: HttpRequestBuilder =
    http("Navigate to piaForDbPension page " + period)
      .get(calculateRoute + postPiaForDbPension2016PageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPostPiaForDbPension2016Confirmation(value: String): HttpRequestBuilder =
    http("piaForDbPensionPageUrl : " + value)
      .post(calculateRoute + postPiaForDbPension2016PageUrl)
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

  val navigateToTaxableIncomePageUrlPage: HttpRequestBuilder =
    http("Navigate to TaxableIncome page " + period)
      .get(calculateRoute + period + taxableIncomePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTaxableIncomePageUrlConfirmation(value: String): HttpRequestBuilder =
    http("TaxableIncomePage : " + value)
      .post(calculateRoute + period + taxableIncomePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToClaimingTaxReliefPage: HttpRequestBuilder =
    http("Navigate to ClaimingTaxRelief page " + period)
      .get(calculateRoute + period + claimingTaxReliefPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitClaimingTaxRelief(value: String): HttpRequestBuilder =
    http("ClaimingTaxRelief : " + value)
      .post(calculateRoute + period + claimingTaxReliefPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToContributeToReliefAtSourceScheme: HttpRequestBuilder =
    http("Navigate to ContributeToReliefAtSourceScheme page " + period)
      .get(calculateRoute + period + contributeToReliefAtSourceSchemePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitContributeToReliefAtSourceScheme(value: String): HttpRequestBuilder =
    http("cContributeToReliefAtSourceScheme : " + value)
      .post(calculateRoute + period + contributeToReliefAtSourceSchemePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTaxReliefPageUrlPage: HttpRequestBuilder =
    http("Navigate to TaxRelief page " + period)
      .get(calculateRoute + period + taxReliefPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTaxRelief(value: String): HttpRequestBuilder =
    http("TaxReliefPage : " + value)
      .post(calculateRoute + period + taxReliefPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHowMuchContributionReliefAtSourceUrlPage: HttpRequestBuilder =
    http("Navigate to HowMuchContributionReliefAtSource page " + period)
      .get(calculateRoute + period + howMuchContributionReliefAtSourcePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowMuchContributionReliefAtSource(value: String): HttpRequestBuilder =
    http("HowMuchContributionReliefAtSource : " + value)
      .post(calculateRoute + period + howMuchContributionReliefAtSourcePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToDonateViaGiftAidUrlPage: HttpRequestBuilder =
    http("Navigate to DonateViaGiftAid page " + period)
      .get(calculateRoute + period + donateViaGiftAidPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDonateViaGiftAid(value: String): HttpRequestBuilder =
    http("DonateViaGiftAid : " + value)
      .post(calculateRoute + period + donateViaGiftAidPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToDonateViaGiftAidAmountUrlPage: HttpRequestBuilder =
    http("Navigate to DonateViaGiftAidAmount page " + period)
      .get(calculateRoute + period + donateViaGiftAidAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDonateViaGiftAidAmount(value: String): HttpRequestBuilder =
    http("DonateViaGiftAidAmount : " + value)
      .post(calculateRoute + period + donateViaGiftAidAmountPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToDoYouKnowPersonalAllowanceUrlPage: HttpRequestBuilder =
    http("Navigate to DoYouKnowPersonalAllowance page " + period)
      .get(calculateRoute + period + doYouKnowPersonalAllowancePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitDoYouKnowPersonalAllowance(value: String): HttpRequestBuilder =
    http("DoYouKnowPersonalAllowance : " + value)
      .post(calculateRoute + period + doYouKnowPersonalAllowancePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToPersonalAllowanceAmountUrlPage: HttpRequestBuilder =
    http("Navigate to PersonalAllowanceAmount page " + period)
      .get(calculateRoute + period + personalAllowanceAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitPersonalAllowanceAmount(value: String): HttpRequestBuilder =
    http("PersonalAllowanceAmount : " + value)
      .post(calculateRoute + period + personalAllowanceAmountPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToBlindPersonAllowanceUrlPage: HttpRequestBuilder =
    http("Navigate to BlindPersonAllowance page " + period)
      .get(calculateRoute + period + blindPersonAllowancePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitBlindPersonAllowance(value: String): HttpRequestBuilder =
    http("BlindPersonAllowance : " + value)
      .post(calculateRoute + period + blindPersonAllowancePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToBlindPersonsAllowanceAmountUrlPage: HttpRequestBuilder =
    http("Navigate to BlindPersonsAllowanceAmount page " + period)
      .get(calculateRoute + period + blindPersonAllowanceAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitBlindPersonsAllowanceAmount(value: String): HttpRequestBuilder =
    http("BlindPersonsAllowanceAmount : " + value)
      .post(calculateRoute + period + blindPersonAllowanceAmountPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAnySalarySacrificeArrangementsUrlPage: HttpRequestBuilder =
    http("Navigate to AnySalarySacrificeArrangements page " + period)
      .get(calculateRoute + period + anySalarySacrificeArrangementsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAnySalarySacrificeArrangements(value: String): HttpRequestBuilder =
    http("AnySalarySacrificeArrangements : " + value)
      .post(calculateRoute + period + anySalarySacrificeArrangementsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAmountSalarySacrificeArrangementsUrlPage: HttpRequestBuilder =
    http("Navigate toAmountSalarySacrificeArrangements page " + period)
      .get(calculateRoute + period + amountSalarySacrificeArrangementsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmountSalarySacrificeArrangements(value: String): HttpRequestBuilder =
    http("AmountSalarySacrificeArrangements : " + value)
      .post(calculateRoute + period + amountSalarySacrificeArrangementsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToFlexibleRemunerationArrangementsUrlPage: HttpRequestBuilder =
    http("Navigate toFlexibleRemunerationArrangements page " + period)
      .get(calculateRoute + period + flexibleRemunerationArrangementsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitFlexibleRemunerationArrangements(value: String): HttpRequestBuilder =
    http("FlexibleRemunerationArrangements : " + value)
      .post(calculateRoute + period + flexibleRemunerationArrangementsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAmountFlexibleRemunerationArrangementsUrlPage: HttpRequestBuilder =
    http("Navigate to AmountFlexibleRemunerationArrangements page " + period)
      .get(calculateRoute + period + amountFlexibleRemunerationArrangementsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAmountFlexibleRemunerationArrangement(value: String): HttpRequestBuilder =
    http("AmountFlexibleRemunerationArrangements : " + value)
      .post(calculateRoute + period + amountSalarySacrificeArrangementsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAnyLumpSumDeathBenefitsUrlPage: HttpRequestBuilder =
    http("Navigate to AnyLumpSumDeathBenefits page " + period)
      .get(calculateRoute + period + anyLumpSumDeathBenefitsPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAnyLumpSumDeathBenefits(value: String): HttpRequestBuilder =
    http("AnyLumpSumDeathBenefits : " + value)
      .post(calculateRoute + period + anyLumpSumDeathBenefitsPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToLumpSumDeathBenefitsValueUrlPage: HttpRequestBuilder =
    http("Navigate to LumpSumDeathBenefitsValue page " + period)
      .get(calculateRoute + period + lumpSumDeathBenefitsValuePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitLumpSumDeathBenefitsValue(value: String): HttpRequestBuilder =
    http("LumpSumDeathBenefitsValue : " + value)
      .post(calculateRoute + period + lumpSumDeathBenefitsValuePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToKnowAdjustedAmountUrlPage: HttpRequestBuilder =
    http("Navigate to KnowAdjustedAmount page " + period)
      .get(calculateRoute + period + knowAdjustedAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitKnowAdjustedAmount(value: String): HttpRequestBuilder =
    http("KnowAdjustedAmount : " + value)
      .post(calculateRoute + period + knowAdjustedAmountPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAdjustedIncomeUrlPage: HttpRequestBuilder =
    http("Navigate toAdjustedIncome page " + period)
      .get(calculateRoute + period + adjustedIncomePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAdjustedIncome(value: String): HttpRequestBuilder =
    http("AdjustedIncome : " + value)
      .post(calculateRoute + period + adjustedIncomePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToClaimingTaxReliefPensionUrlPage: HttpRequestBuilder =
    http("Navigate to ClaimingTaxReliefPension page " + period)
      .get(calculateRoute + period + claimingTaxReliefPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitClaimingTaxReliefPension(value: String): HttpRequestBuilder =
    http("ClaimingTaxReliefPension : " + value)
      .post(calculateRoute + period + claimingTaxReliefPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHowMuchTaxReliefPensionUrlPage: HttpRequestBuilder =
    http("Navigate to HowMuchTaxReliefPension page " + period)
      .get(calculateRoute + period + howMuchTaxReliefPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowMuchTaxReliefPension(value: String): HttpRequestBuilder =
    http("HowMuchTaxReliefPension : " + value)
      .post(calculateRoute + period + howMuchTaxReliefPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToHowMuchContributionUrlPage: HttpRequestBuilder =
    http("Navigate to HowMuchContribution page " + period)
      .get(calculateRoute + period + howMuchContributionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitHowMuchContribution(value: String): HttpRequestBuilder =
    http("HowMuchContribution : " + value)
      .post(calculateRoute + period + howMuchContributionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToAnyTaxReliefOverseasPensionUrlPage: HttpRequestBuilder =
    http("Navigate to AnyTaxReliefOverseasPension page " + period)
      .get(calculateRoute + period + anyTaxReliefOverSeasPensionPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitAnyTaxReliefOverseasPension(value: String): HttpRequestBuilder =
    http("AnyTaxReliefOverseasPension : " + value)
      .post(calculateRoute + period + anyTaxReliefOverSeasPensionPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTaxReliefOverseasPensionValueUrlPage: HttpRequestBuilder =
    http("Navigate to TaxReliefOverseasPensionValue page " + period)
      .get(calculateRoute + period + taxReliefOverseasPensionValuePageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTaxReliefOverseasPensionValue(value: String): HttpRequestBuilder =
    http("TaxReliefOverseasPensionValue : " + value)
      .post(calculateRoute + period + taxReliefOverseasPensionValuePageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToTradeUnionReliefUrlPage: HttpRequestBuilder =
    http("Navigate to TradeUnionRelief page " + period)
      .get(calculateRoute + period + tradeUnionReliefPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitTradeUnionRelief(value: String): HttpRequestBuilder =
    http("TradeUnionRelief : " + value)
      .post(calculateRoute + period + tradeUnionReliefPageUrl)
      .formParam("csrfToken", "${csrfToken}")
      .formParam("value", value)
      .check(status.is(303))

  val navigateToUnionPoliceReliefAmountUrlPage: HttpRequestBuilder =
    http("Navigate to UnionPoliceReliefAmount page " + period)
      .get(calculateRoute + period + unionPoliceReliefAmountPageUrl)
      .check(status.is(200))
      .check(saveCsrfToken)

  def submitUnionPoliceReliefAmount(value: String): HttpRequestBuilder =
    http("UnionPoliceReliefAmount : " + value)
      .post(calculateRoute + period + unionPoliceReliefAmountPageUrl)
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
