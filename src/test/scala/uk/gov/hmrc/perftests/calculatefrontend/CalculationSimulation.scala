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

package uk.gov.hmrc.perftests.calculatefrontend

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.calculatefrontend.requests.auth.AuthRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.AAPeriodRequests
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.AAWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.BasicWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateLTA.LTARequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.showCalculation.ShowCalculationRequests.verifyShowCalculationResults
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests._
import uk.gov.hmrc.perftests.calculatefrontend.util.NINOGenerator

class CalculationSimulation extends PerformanceTestRunner {

  val pension_scheme_name = "pensionSchemeName"
  val taxRef              = "00348916RX"
  val periodRequests2016  = new AAPeriodRequests("2016")
  val periodRequests2017  = new AAPeriodRequests("2017")

  setup("Basic-flow", "Basic user journey")
    .withRequests(
      navigateToResubmittingAdjustmentPage,
      submitResubmittingConfirmation("false"),
      navigateToAffectedByRemedyPage,
      submitAffectedByRemedy("true"),
      navigateToReportingChangePage,
      submitReportingChangeConfirmation("annualAllowance", "lifetimeAllowance"),
      navigateToReceivedLetterPage,
      submitReceivedLetter("true"),
      navigateToProtectedMemberPage,
      submitProtectedMember("false"),
      navigateToAnnualAllowanceChargePage,
      submitAnnualAllowanceCharge("false"),
      navigateToContributionRefundsPage,
      submitContributionRefunds("true"),
      navigateToIncomeOver100Page,
      submitIncomeOver100("false"),
      navigateToIncomeOver190_22Page,
      submitIncomeOver190_22("false"),
      navigateToPIAAmountIncreasedPage,
      submitPIAAmountIncreased("idk"),
      navigateToPIAAmountDecreaseOrNoChangePage,
      submitPIAAmountDecreaseOrNoChange("yes"),
      navigateToPIAAboveAnnualAllowanceLimitPage,
      submitPIAAboveAnnualAllowanceLimit("false"),
      navigateToIncomeOver190_23Page,
      submitIncomeOver190_23("false"),
      navigateToFlexibleAccessDCSchemePage,
      submitFlexibleAccessDCScheme("true"),
      navigateTo4000ContributionToDirectContributionSchemePage,
      submit4000ContributionToDirectContributionScheme("true"),
      navigateToHadBenefitCrystallisationEventPage,
      submitHadBenefitCrystallisationEventConfirmation("true"),
      navigateToLTAChargePage,
      submitLTACharge("false"),
      navigateToLifeTimeAllowancePercentageChangePage,
      submitLifeTimeAllowancePercentageChange("true"),
      navigateToLifeTimeAllowancePercentageIncreasePage,
      submitLifeTimeAllowancePercentageIncrease("true"),
      navigateToLifeTimeAllowanceNewChargePage,
      submitLifeTimeAllowanceNewCharge("false"),
      navigateToMoreThanOneBenefitCrystallisationEventPage,
      submitMoreThanOneBCE("true"),
      navigateToOtherSchemeLTANotificationPage,
      submitOtherSchemeLTANotification("true"),
      navigateToCheckYourAnswersPage
    )

  setup("Annual-Allowance-flow", "Annual allowance user journey")
    .withRequests(
      navigateToScottishTaxpayerFrom2016Page,
      submitScottishTaxpayerFrom2016Confirmation("true"),
      navigateToWhichYearsScottishTaxpayerPage,
      submitWhichYearsScottishTaxpayerPageConfirmation(),
      navigateToPayingIntoPublicPensionSchemePageUrlPage,
      submitPayingIntoPublicPensionSchemePageUrlPageConfirmation("true"),
      navigateToHaveDefinedContributionPensionPageUrlPage,
      submitHaveDefinedContributionPensionPageConfirmation("true"),
      navigateToHaveFlexiblyAccessedPensionUrlPage,
      submitHaveFlexiblyAccessedPensionPageConfirmation("false"),
      navigateToPayTaxCharge20142015UrlPage,
      submitPayTaxCharge20142015Confirmation("false"),
      navigateToPayTaxChargeFrom20102011UrlPage,
      submitPayTaxChargeFrom20102011Confirmation("true"),
      navigateToPiaPreRemedy2011Page,
      submitPiaPreRemedy2011PageConfirmation("40000"),
      navigateToPayTaxChargeFrom20112012UrlPage,
      submitPayTaxChargeFrom20112012Confirmation("true"),
      navigateToPiaPreRemedy2012Page,
      submitPiaPreRemedy2012PageConfirmation("40000"),
      navigateToPayTaxChargeFrom20122013UrlPage,
      submitPayTaxChargeFrom20122013Confirmation("true"),
      navigateToPiaPreRemedy2013Page,
      submitPiaPreRemedy2013PageConfirmation("40000"),
      navigateToPayTaxChargeFrom20132014UrlPage,
      submitPayTaxChargeFrom20132014Confirmation("true"),
      navigateToPiaPreRemedy2014Page,
      submitPiaPreRemedy2014PageConfirmation("40000"),
      navigateToPayTaxChargeFrom20142015UrlPage,
      submitPayTaxChargeFrom20142015Confirmation("true"),
      navigateToPiaPreRemedy2015Page,
      submitPiaPreRemedy2015PageConfirmation("40000"),
      navigateToCheckYourAnswersAnnualAllowanceSetupPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2016", "2016 calculation")
    .withRequests(
      periodRequests2016.navigateToWhatYouWillNeedAaPage,
      periodRequests2016.navigateToMemberMoreThanOnePensionPage,
      periodRequests2016.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2016.navigateToWhichSchemeDetailsPage,
      periodRequests2016.submitWhichSchemeDetailsConfirmation("00348916RS"),
      periodRequests2016.navigateToFirstPensionSchemeInputAmount1516period1UrlPage,
      periodRequests2016.submitFirstPensionSchemeInputAmount1516period1("25000"),
      periodRequests2016.navigateToFirstPensionSchemeInputAmount1516period2UrlPage,
      periodRequests2016.submitFirstPensionSchemeInputAmount1516period2("25000"),
      periodRequests2016.navigateToDidYouPayAChargePage,
      periodRequests2016.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2016.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2016.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2016.navigateToHowMuchYouPayChargePage,
      periodRequests2016.submitHowMuchYouPayChargeConfirmation("3200"),
      periodRequests2016.navigateToAddAnotherSchemePage,
      periodRequests2016.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2016.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2016.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2016.navigateToSecondPensionSchemeInputAmount1516period1UrlPage,
      periodRequests2016.submitSecondPensionSchemeInputAmount1516period1("15000"),
      periodRequests2016.navigateToSecondPensionSchemeInputAmount1516period2UrlPage,
      periodRequests2016.submitSecondPensionSchemeInputAmount1516period2("15000"),
      periodRequests2016.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2016.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2016.navigateToAddAnotherSchemeTwoPage,
      periodRequests2016.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2016.navigateToContributedOtherDbDcSchemePage,
      periodRequests2016.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2016.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2016.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2016.navigateToPrePiaForDb2016PensionPage,
      periodRequests2016.submitPrePiaForDbPension2016Confirmation("40000"),
      periodRequests2016.navigateToPostPiaForDb2016PensionPage,
      periodRequests2016.submitPostPiaForDbPension2016Confirmation("40000"),
      periodRequests2016.navigateToTaxableIncomePageUrlPage,
      periodRequests2016.submitTaxableIncomePageUrlConfirmation("60000"),
      periodRequests2016.navigateToClaimingTaxReliefPage,
      periodRequests2016.submitClaimingTaxRelief("true"),
      periodRequests2016.navigateToTaxReliefPageUrlPage,
      periodRequests2016.submitTaxRelief("1000"),
      periodRequests2016.navigateToContributeToReliefAtSourceScheme,
      periodRequests2016.submitContributeToReliefAtSourceScheme("true"),
      periodRequests2016.navigateToHowMuchContributionReliefAtSourceUrlPage,
      periodRequests2016.submitHowMuchContributionReliefAtSource("1000"),
      periodRequests2016.navigateToDonateViaGiftAidUrlPage,
      periodRequests2016.submitDonateViaGiftAid("true"),
      periodRequests2016.navigateToDonateViaGiftAidAmountUrlPage,
      periodRequests2016.submitDonateViaGiftAidAmount("1000"),
      periodRequests2016.navigateToDoYouKnowPersonalAllowanceUrlPage,
      periodRequests2016.submitDoYouKnowPersonalAllowance("true"),
      periodRequests2016.navigateToPersonalAllowanceAmountUrlPage,
      periodRequests2016.submitPersonalAllowanceAmount("1000"),
      periodRequests2016.navigateToBlindPersonAllowanceUrlPage,
      periodRequests2016.submitBlindPersonAllowance("true"),
      periodRequests2016.navigateToBlindPersonsAllowanceAmountUrlPage,
      periodRequests2016.submitBlindPersonsAllowanceAmount("2290"),
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2017", "2017 calculation")
    .withRequests(
      periodRequests2017.navigateToWhatYouWillNeedAaPage,
      periodRequests2017.navigateToMemberMoreThanOnePensionPage,
      periodRequests2017.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2017.navigateToWhichSchemeDetailsPage,
      periodRequests2017.submitWhichSchemeDetailsConfirmation("00348916RB"),
      periodRequests2017.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2017.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("25000"),
      periodRequests2017.navigateToDidYouPayAChargePage,
      periodRequests2017.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2017.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2017.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2017.navigateToHowMuchYouPayChargePage,
      periodRequests2017.submitHowMuchYouPayChargeConfirmation("1200"),
      periodRequests2017.navigateToAddAnotherSchemePage,
      periodRequests2017.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2017.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2017.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2017.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2017.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("13000"),
      periodRequests2017.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2017.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2017.navigateToAddAnotherSchemeTwoPage,
      periodRequests2017.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2017.navigateToContributedOtherDbDcSchemePage,
      periodRequests2017.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2017.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2017.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2017.navigateToPiaForDbPensionPage,
      periodRequests2017.submitPiaForDbPensionConfirmation("38000"),
      periodRequests2017.navigateToThresholdIncomePage,
      periodRequests2017.submitThresholdIncomePageConfirmation("idk"),
      periodRequests2017.navigateToTaxableIncomePageUrlPage,
      periodRequests2017.submitTaxableIncomePageUrlConfirmation("135000"),
      periodRequests2017.navigateToAnySalarySacrificeArrangementsUrlPage,
      periodRequests2017.submitAnySalarySacrificeArrangements("true"),
      periodRequests2017.navigateToAmountSalarySacrificeArrangementsUrlPage,
      periodRequests2017.submitAmountSalarySacrificeArrangements("1000"),
      periodRequests2017.navigateToFlexibleRemunerationArrangementsUrlPage,
      periodRequests2017.submitFlexibleRemunerationArrangements("true"),
      periodRequests2017.navigateToAmountFlexibleRemunerationArrangementsUrlPage,
      periodRequests2017.submitAmountFlexibleRemunerationArrangement("1000"),
      periodRequests2017.navigateToAnyLumpSumDeathBenefitsUrlPage,
      periodRequests2017.submitAnyLumpSumDeathBenefits("true"),
      periodRequests2017.navigateToLumpSumDeathBenefitsValueUrlPage,
      periodRequests2017.submitLumpSumDeathBenefitsValue("1000"),
      periodRequests2017.navigateToClaimingTaxReliefPage,
      periodRequests2017.submitClaimingTaxRelief("true"),
      periodRequests2017.navigateToTaxReliefPageUrlPage,
      periodRequests2017.submitTaxRelief("1000"),
      periodRequests2017.navigateToContributeToReliefAtSourceScheme,
      periodRequests2017.submitContributeToReliefAtSourceScheme("true"),
      periodRequests2017.navigateToHowMuchContributionReliefAtSourceUrlPage,
      periodRequests2017.submitHowMuchContributionReliefAtSource("1000"),
      periodRequests2017.navigateToKnowAdjustedAmountUrlPage,
      periodRequests2017.submitKnowAdjustedAmount("false"),
      periodRequests2017.navigateToClaimingTaxReliefPensionUrlPage,
      periodRequests2017.submitClaimingTaxReliefPension("true"),
      periodRequests2017.navigateToHowMuchTaxReliefPensionUrlPage,
      periodRequests2017.submitHowMuchTaxReliefPension("1000"),
      periodRequests2017.navigateToHowMuchContributionUrlPage,
      periodRequests2017.submitHowMuchContribution("1000"),
      periodRequests2017.navigateToAnyTaxReliefOverseasPensionUrlPage,
      periodRequests2017.submitAnyTaxReliefOverseasPension("true"),
      periodRequests2017.navigateToTaxReliefOverseasPensionValueUrlPage,
      periodRequests2017.submitTaxReliefOverseasPensionValue("1000"),
      periodRequests2017.navigateToDonateViaGiftAidAmountUrlPage,
      periodRequests2016.submitDonateViaGiftAid("false"),
      periodRequests2017.navigateToDoYouKnowPersonalAllowanceUrlPage,
      periodRequests2017.submitDoYouKnowPersonalAllowance("false"),
      periodRequests2017.navigateToTradeUnionReliefUrlPage,
      periodRequests2017.submitTradeUnionRelief("true"),
      periodRequests2017.navigateToUnionPoliceReliefAmountUrlPage,
      periodRequests2017.submitUnionPoliceReliefAmount("100"),
      periodRequests2017.navigateToBlindPersonAllowanceUrlPage,
      periodRequests2017.submitBlindPersonAllowance("true"),
      periodRequests2017.navigateToBlindPersonsAllowanceAmountUrlPage,
      periodRequests2017.submitBlindPersonsAllowanceAmount("2290"),
      navigateToTaskListPageUrlPage
    )

  setup("Show-Calculation", "Calculation submission")
    .withRequests(
      navigateToCalculationResultPageUrlPage,
      verifyShowCalculationResults()
    )

  setup("Submission-landing", "Landing to submission")
    .withActions(calculationUniqueID.actionBuilders: _*)
    .withActions(calculationSessionId.actionBuilders: _*)
    .withRequests(
      getSubmissionUniqueId(),
      submitUserAnswers(),
      loginForSubmission(),
      navigateToSubmissionLandingPage
    )

  setup("Submission-route", "Submission-route")
    .withRequests(
      navigateToSubmissionInforPage,
      navigateToClaimOnBehalfPage,
      submitClaimOnBehalfPageUrlConfirmation("true"),
      navigateToStatusOfUserPage,
      submitStatusOfUserPageConfirmation("deputyship"),
      navigateToTheirNamePage,
      submitTheirNamePageConfirmation("ABC BCDEFGHIJK"),
      navigateToTheirDobPage,
      submitTheirDobPageConfirmation(),
      navigateToTheirDateOfDeathPage,
      submitTheirDateOfDeathPageConfirmation(),
      navigateToTheirNinoPage,
      submitTheirNinoPageUrlConfirmation(NINOGenerator.generateNINO),
      navigateToTheirUTRPage,
      submitTheirUTRPConfirmation(),
      navigateToTheirResidencePage,
      submitTheirResidencePageConfirmation("true"),
      navigateToTheirUKAddressPage,
      submitTheirUKAddressPageConfirmation(),
      navigateToAlternativeNamePage,
      submitAlternativeNamePageConfirmation("false"),
      navigateToEnterAlternativeNamePage,
      submitEnterAlternativeNamePageConfirmation("ABC BCDEFGH"),
      navigateToContactNumberPage,
      submitContactNumberPageConfirmation(),
      navigateToResidencePage,
      submitResidencePagConfirmation("false"),
      navigateToInternationalAddressPagePage,
      submitInternationalAddressPageConfirmation(),
      navigateToLegacyPensionSchemeReferencePage,
      submitLegacyPensionSchemeReferencePageConfirmation(),
      navigateToReformPensionSchemeReferencePageUrlPage,
      submitReformPensionSchemeReferencePageUrlConfirmation(),
      navigateToClaimingAdditionalTaxRateReliefPageUrlPage,
      submitClaimingAdditionalTaxRateReliefPageUrlConfirmation(),
      navigateToTaxReliefAmountPage,
      submitTaxReliefAmountPageUrlConfirmation(),
      navigateToWhichPensionSchemeWillPayPage,
      submitWhichPensionSchemeWillPayPageUrlConfirmation(),
      navigateToCheckYourAnswersSubmitPage,
      navigateToDeclarationsPage
    )

  setup("Final-Submission", "Final-Submission")
    .withRequests(
      getSubmission(),
      getSubmissionBearerToken(),
      submitCheckYourAnswersSubmitPageConfirmation()
    )

  runSimulation()
}
