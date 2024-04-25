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
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.AAWorkFlowRequests.{navigateToCalculationResultPageUrlPage, navigateToCheckYourAnswersAnnualAllowanceSetupPage, navigateToHaveDefinedContributionPensionPageUrlPage, navigateToHaveFlexiblyAccessedPensionUrlPage, navigateToPayTaxChargeBetween20142015UrlPage, navigateToPayTaxChargeFrom20102011UrlPage, navigateToPayTaxChargeFrom20112012UrlPage, navigateToPayTaxChargeFrom20122013UrlPage, navigateToPayTaxChargeFrom20132014UrlPage, navigateToPayTaxChargeFrom20142015UrlPage, navigateToPayTaxChargeFrom20152016UrlPage, navigateToPayingIntoPublicPensionSchemePageUrlPage, navigateToPiaPreRemedy2011Page, navigateToPiaPreRemedy2012Page, navigateToPiaPreRemedy2013Page, navigateToPiaPreRemedy2014Page, navigateToPiaPreRemedy2015Page, navigateToScottishTaxpayerFrom2016Page, navigateToTaskListPageUrlPage, navigateToWhichYearsScottishTaxpayerPage, submitHaveDefinedContributionPensionPageConfirmation, submitHaveFlexiblyAccessedPensionPageConfirmation, submitPayTaxChargeBetween20142015Confirmation, submitPayTaxChargeFrom20102011Confirmation, submitPayTaxChargeFrom20112012Confirmation, submitPayTaxChargeFrom20122013Confirmation, submitPayTaxChargeFrom20132014Confirmation, submitPayTaxChargeFrom20142015Confirmation, submitPayTaxChargeFrom20152016Confirmation, submitPayingIntoPublicPensionSchemePageUrlPageConfirmation, submitPiaPreRemedy2011PageConfirmation, submitPiaPreRemedy2012PageConfirmation, submitPiaPreRemedy2013PageConfirmation, submitPiaPreRemedy2014PageConfirmation, submitPiaPreRemedy2015PageConfirmation, submitScottishTaxpayerFrom2016Confirmation, submitWhichYearsScottishTaxpayerPageConfirmation}
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.BasicWorkFlowRequests.{navigateToCheckYourAnswersPage, navigateToReportingChangePage, navigateToResubmittingAdjustmentPage, navigateToSavingsStatementPage, submitReportingChangeConfirmation, submitResubmittingConfirmation, submitRssReceivedConfirmation}
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
      navigateToSavingsStatementPage,
      submitRssReceivedConfirmation("true"),
      navigateToResubmittingAdjustmentPage,
      submitResubmittingConfirmation("false"),
      navigateToReportingChangePage,
      submitReportingChangeConfirmation("annualAllowance", "lifetimeAllowance"),
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
      navigateToPayTaxChargeBetween20142015UrlPage,
      submitPayTaxChargeBetween20142015Confirmation("false"),
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
      periodRequests2016.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("30000", "25000"),
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
      periodRequests2016.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("18000", "15000"),
      periodRequests2016.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2016.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2016.navigateToAddAnotherSchemeTwoPage,
      periodRequests2016.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2016.navigateToContributedOtherDbDcSchemePage,
      periodRequests2016.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2016.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2016.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2016.navigateToPiaForDbPensionPage,
      periodRequests2016.submitPiaForDbPensionConfirmation("40000"),
      periodRequests2016.navigateToTotalIncomePageUrlPage,
      periodRequests2016.submitTotalIncomePageUrlConfirmation("60000"),
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
      periodRequests2017.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("28000", "25000"),
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
      periodRequests2017.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("15000", "13000"),
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
      periodRequests2017.submitThresholdIncomePageConfirmation("false"),
      periodRequests2017.navigateToTotalIncomePageUrlPage,
      periodRequests2017.submitTotalIncomePageUrlConfirmation("60000"),
      navigateToTaskListPageUrlPage
    )

  setup("Show-Calculation", "Calculation submission")
    .withRequests(
      navigateToCalculationResultPageUrlPage,
      verifyShowCalculationResults()
    )

  setup("Auth-wizard", "Authorization")
    .withActions(uuidFeeder.actionBuilders: _*)
    .withRequests(
      getSubmissionUniqueId(),
      submitUserAnswers(),
      loginForSubmission(),
      navigateToAuthPage
    )

  setup("Submission-route", "Submission-route")
    .withRequests(
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
      submitTheirNinoPageUrlConfirmation(NINOGenerator.nino),
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
      navigateToLegacyPensionScheme2ReferencePage,
      submitLegacyPensionScheme2ReferencePageConfirmation(),
      navigateToReformPensionScheme2ReferencePageUrlPage,
      submitReformPensionScheme2ReferencePageUrlConfirmation(),
      navigateToLegacyPensionScheme3ReferencePage,
      submitLegacyPensionScheme3ReferencePageConfirmation(),
      navigateToReformPensionScheme3ReferencePageUrlPage,
      submitReformPensionScheme3ReferencePageUrlConfirmation(),
      navigateToClaimingAdditionalTaxRateReliefPageUrlPage,
      submitClaimingAdditionalTaxRateReliefPageUrlConfirmation(),
      navigateToTaxReliefAmountPage,
      submitTaxReliefAmountPageUrlConfirmation(),
      navigateToWhichPensionSchemeWillPayPage,
      submitWhichPensionSchemeWillPayPageUrlConfirmation(),
      navigateToDeclarationsPage,
      navigateToCheckYourAnswersSubmitPage
    )

  setup("Final-Submission", "Final-Submission")
    .withRequests(
      getSubmission(),
      getSubmissionBearerToken(),
      submitCheckYourAnswersSubmitPageConfirmation()
    )

  runSimulation()
}
