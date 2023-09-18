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

package uk.gov.hmrc.perftests.calculatefrontend

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.calculatefrontend.requests.auth.AuthRequests.{getSubmissionUniqueId, loginForSubmission, navigateToAuthPage, navigateToAuthWizardSession}
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.AAWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.BasicWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.CalculationRequests.{navigateToCalculationResultPage, submitCalculationResultConfirmation}
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateLTA.LTARequests._
import uk.gov.hmrc.perftests.calculatefrontend.util.NINOGenerator
//import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests.{navigateToClaimOnBehalfPage, navigateToStatusOfUserPage, navigateToTheirDateOfDeathPage, navigateToTheirDobPage, navigateToTheirNamePage, submitClaimOnBehalfPageUrlConfirmation, submitStatusOfUserPageConfirmation, submitTheirDateOfDeathPageConfirmation, submitTheirDobPageConfirmation, submitTheirNamePageConfirmation}
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests._
class CalculationSimulation extends PerformanceTestRunner {

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
      navigateToPayTaxChargeFrom20152016UrlPage,
      submitPayTaxChargeFrom20152016Confirmation("false"),
      navigateToPiaPreRemedy2013Page,
      submitPiaPreRemedy2013PageConfirmation("40000"),
      navigateToPiaPreRemedy2014Page,
      submitPiaPreRemedy2014PageConfirmation("40000"),
      navigateToPiaPreRemedy2015Page,
      submitPiaPreRemedy2015PageConfirmation("40000"),
      navigateToCheckYourAnswersAnnualAllowanceSetupPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2016Pre", "2016 pre calculation")
    .withRequests(
      AA2016PreRequests.navigateToWhatYouWillNeedAaPage,
      AA2016PreRequests.navigateToMemberMoreThanOnePensionPage,
      AA2016PreRequests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2016PreRequests.navigateToFirstPensionSchemeDetailsPage,
      AA2016PreRequests.submitPensionFirstSchemeDetailsConfirmation("Scheme 1", "00348916RT"),
      AA2016PreRequests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2016PreRequests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("20000", "15000"),
      AA2016PreRequests.navigateToDidYouPayAChargePage,
      AA2016PreRequests.submitDidYouPayAChargeConfirmation("false"),
      AA2016PreRequests.navigateToAddAnotherSchemePage,
      AA2016PreRequests.submitAddAnotherSchemeConfirmation("true"),
      AA2016PreRequests.navigateToSecondPensionSchemeDetailsPage,
      AA2016PreRequests.submitPensionSecondSchemeDetailsConfirmation("Scheme 2", "00348916RG"),
      AA2016PreRequests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2016PreRequests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("20000", "18000"),
      AA2016PreRequests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2016PreRequests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2016PreRequests.navigateToAddAnotherSchemeTwoPage,
      AA2016PreRequests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2016PreRequests.navigateToContributedOtherDbDcSchemePage,
      AA2016PreRequests.submitContributedOtherDbDcSchemeConfirmation("true"),
      AA2016PreRequests.navigateToWhichContributedDuringRemedyPeriodPage,
      AA2016PreRequests.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      AA2016PreRequests.navigateToPiaForDbPensionPage,
      AA2016PreRequests.submitPiaForDbPensionConfirmation("33000"),
      AA2016PreRequests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2016Post", "2016 post calculation")
    .withRequests(
      AA2016PostRequests.navigateToWhatYouWillNeedAaPage,
      AA2016PostRequests.navigateToMemberMoreThanOnePensionPage,
      AA2016PostRequests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2016PostRequests.navigateToWhichSchemeDetailsPage,
      AA2016PostRequests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2016PostRequests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2016PostRequests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("30000", "25000"),
      AA2016PostRequests.navigateToDidYouPayAChargePage,
      AA2016PostRequests.submitDidYouPayAChargeConfirmation("true"),
      AA2016PostRequests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2016PostRequests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2016PostRequests.navigateToHowMuchYouPayChargePage,
      AA2016PostRequests.submitHowMuchYouPayChargeConfirmation("3200"),
      AA2016PostRequests.navigateToAddAnotherSchemePage,
      AA2016PostRequests.submitAddAnotherSchemeConfirmation("true"),
      AA2016PostRequests.navigateToWhichSecondSchemeDetailsPage,
      AA2016PostRequests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2016PostRequests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2016PostRequests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("18000", "15000"),
      AA2016PostRequests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2016PostRequests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2016PostRequests.navigateToAddAnotherSchemeTwoPage,
      AA2016PostRequests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2016PostRequests.navigateToContributedOtherDbDcSchemePage,
      AA2016PostRequests.submitContributedOtherDbDcSchemeConfirmation("true"),
      AA2016PostRequests.navigateToWhichContributedDuringRemedyPeriodPage,
      AA2016PostRequests.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      AA2016PostRequests.navigateToPiaForDbPensionPage,
      AA2016PostRequests.submitPiaForDbPensionConfirmation("40000"),
      AA2016PostRequests.navigateToTotalIncomePageUrlPage,
      AA2016PostRequests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2016PostRequests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2017", "2017 calculation")
    .withRequests(
      AA2017Requests.navigateToWhatYouWillNeedAaPage,
      AA2017Requests.navigateToMemberMoreThanOnePensionPage,
      AA2017Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2017Requests.navigateToWhichSchemeDetailsPage,
      AA2017Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2017Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2017Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("28000", "25000"),
      AA2017Requests.navigateToDidYouPayAChargePage,
      AA2017Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2017Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2017Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2017Requests.navigateToHowMuchYouPayChargePage,
      AA2017Requests.submitHowMuchYouPayChargeConfirmation("1200"),
      AA2017Requests.navigateToAddAnotherSchemePage,
      AA2017Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2017Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2017Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2017Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2017Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("15000", "13000"),
      AA2017Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2017Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2017Requests.navigateToAddAnotherSchemeTwoPage,
      AA2017Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2017Requests.navigateToContributedOtherDbDcSchemePage,
      AA2017Requests.submitContributedOtherDbDcSchemeConfirmation("true"),
      AA2017Requests.navigateToWhichContributedDuringRemedyPeriodPage,
      AA2017Requests.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      AA2017Requests.navigateToPiaForDbPensionPage,
      AA2017Requests.submitPiaForDbPensionConfirmation("38000"),
      AA2017Requests.navigateToThresholdIncomePage,
      AA2017Requests.submitThresholdIncomePageConfirmation("false"),
      AA2017Requests.navigateToTotalIncomePageUrlPage,
      AA2017Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2017Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2018", "2018 calculation")
    .withRequests(
      AA2018Requests.navigateToWhatYouWillNeedAaPage,
      AA2018Requests.navigateToMemberMoreThanOnePensionPage,
      AA2018Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2018Requests.navigateToWhichSchemeDetailsPage,
      AA2018Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2018Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2018Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2018Requests.navigateToDidYouPayAChargePage,
      AA2018Requests.submitDidYouPayAChargeConfirmation("false"),
      AA2018Requests.navigateToAddAnotherSchemePage,
      AA2018Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2018Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2018Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2018Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2018Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("30000", "25000"),
      AA2018Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2018Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2018Requests.navigateToAddAnotherSchemeTwoPage,
      AA2018Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2018Requests.navigateToContributedOtherDbDcSchemePage,
      AA2018Requests.submitContributedOtherDbDcSchemeConfirmation("true"),
      AA2018Requests.navigateToWhichContributedDuringRemedyPeriodPage,
      AA2018Requests.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      AA2018Requests.navigateToPiaForDbPensionPage,
      AA2018Requests.submitPiaForDbPensionConfirmation("38000"),
      AA2018Requests.navigateToThresholdIncomePage,
      AA2018Requests.submitThresholdIncomePageConfirmation("false"),
      AA2018Requests.navigateToTotalIncomePageUrlPage,
      AA2018Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2018Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2019", "2019 calculation")
    .withRequests(
      AA2019Requests.navigateToWhatYouWillNeedAaPage,
      AA2019Requests.navigateToMemberMoreThanOnePensionPage,
      AA2019Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2019Requests.navigateToWhichSchemeDetailsPage,
      AA2019Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2019Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2019Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2019Requests.navigateToDidYouPayAChargePage,
      AA2019Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2019Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2019Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2019Requests.navigateToHowMuchYouPayChargePage,
      AA2019Requests.submitHowMuchYouPayChargeConfirmation("3280"),
      AA2019Requests.navigateToAddAnotherSchemePage,
      AA2019Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2019Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2019Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2019Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2019Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("48000", "43000"),
      AA2019Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2019Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2019Requests.navigateToAddAnotherSchemeTwoPage,
      AA2019Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2019Requests.navigateToContributedOtherDbDcSchemePage,
      AA2019Requests.submitContributedOtherDbDcSchemeConfirmation("false"),
      AA2019Requests.navigateToThresholdIncomePage,
      AA2019Requests.submitThresholdIncomePageConfirmation("false"),
      AA2019Requests.navigateToTotalIncomePageUrlPage,
      AA2019Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2019Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2020", "2020 calculation")
    .withRequests(
      AA2020Requests.navigateToWhatYouWillNeedAaPage,
      AA2020Requests.navigateToMemberMoreThanOnePensionPage,
      AA2020Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2020Requests.navigateToWhichSchemeDetailsPage,
      AA2020Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2020Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2020Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2020Requests.navigateToDidYouPayAChargePage,
      AA2020Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2020Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2020Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2020Requests.navigateToHowMuchYouPayChargePage,
      AA2020Requests.submitHowMuchYouPayChargeConfirmation("800"),
      AA2020Requests.navigateToAddAnotherSchemePage,
      AA2020Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2020Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2020Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2020Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2020Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      AA2020Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2020Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2020Requests.navigateToAddAnotherSchemeTwoPage,
      AA2020Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2020Requests.navigateToContributedOtherDbDcSchemePage,
      AA2020Requests.submitContributedOtherDbDcSchemeConfirmation("false"),
      AA2020Requests.navigateToThresholdIncomePage,
      AA2020Requests.submitThresholdIncomePageConfirmation("false"),
      AA2020Requests.navigateToTotalIncomePageUrlPage,
      AA2020Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2020Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2021", "2021 calculation")
    .withRequests(
      AA2021Requests.navigateToWhatYouWillNeedAaPage,
      AA2021Requests.navigateToMemberMoreThanOnePensionPage,
      AA2021Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2021Requests.navigateToWhichSchemeDetailsPage,
      AA2021Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2021Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2021Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2021Requests.navigateToDidYouPayAChargePage,
      AA2021Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2021Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2021Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2021Requests.navigateToHowMuchYouPayChargePage,
      AA2021Requests.submitHowMuchYouPayChargeConfirmation("800"),
      AA2021Requests.navigateToAddAnotherSchemePage,
      AA2021Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2021Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2021Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2021Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2021Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      AA2021Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2021Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2021Requests.navigateToAddAnotherSchemeTwoPage,
      AA2021Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2021Requests.navigateToContributedOtherDbDcSchemePage,
      AA2021Requests.submitContributedOtherDbDcSchemeConfirmation("false"),
      AA2021Requests.navigateToThresholdIncomePage,
      AA2021Requests.submitThresholdIncomePageConfirmation("false"),
      AA2021Requests.navigateToTotalIncomePageUrlPage,
      AA2021Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2021Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2022", "2022 calculation")
    .withRequests(
      AA2022Requests.navigateToWhatYouWillNeedAaPage,
      AA2022Requests.navigateToMemberMoreThanOnePensionPage,
      AA2022Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2022Requests.navigateToWhichSchemeDetailsPage,
      AA2022Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2022Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2022Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2022Requests.navigateToDidYouPayAChargePage,
      AA2022Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2022Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2022Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2022Requests.navigateToHowMuchYouPayChargePage,
      AA2022Requests.submitHowMuchYouPayChargeConfirmation("800"),
      AA2022Requests.navigateToAddAnotherSchemePage,
      AA2022Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2022Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2022Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2022Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2022Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      AA2022Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2022Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2022Requests.navigateToAddAnotherSchemeTwoPage,
      AA2022Requests.submitAddAnotherSchemeTwoConfirmation("true"),
      AA2022Requests.navigateToWhichThirdSchemeDetailsPage,
      AA2022Requests.submitWhichThirdSchemeDetailsConfirmation("New"),
      AA2022Requests.navigateToThirdPensionSchemeDetailsPage,
      AA2022Requests.submitPensionThirdSchemeDetailsConfirmation("Scheme 3", "00348916RF"),
      AA2022Requests.navigateToThirdPensionSchemeInputAmountsPageUrlPage,
      AA2022Requests.submitPensionThirdSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      AA2022Requests.navigateToDidYouPayAChargeThirdSchemePage,
      AA2022Requests.submitDidYouPayAChargeThirdSchemeConfirmation("false"),
      AA2022Requests.navigateToAddAnotherSchemeThreePage,
      AA2022Requests.submitAddAnotherSchemeThreeConfirmation("false"),
      AA2022Requests.navigateToContributedOtherDbDcSchemePage,
      AA2022Requests.submitContributedOtherDbDcSchemeConfirmation("false"),
      AA2022Requests.navigateToThresholdIncomePage,
      AA2022Requests.submitThresholdIncomePageConfirmation("false"),
      AA2022Requests.navigateToTotalIncomePageUrlPage,
      AA2022Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2022Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2023", "2023 calculation")
    .withRequests(
      AA2023Requests.navigateToWhatYouWillNeedAaPage,
      AA2023Requests.navigateToMemberMoreThanOnePensionPage,
      AA2023Requests.submitMemberMoreThanOnePensionConfirmation("true"),
      AA2023Requests.navigateToWhichSchemeDetailsPage,
      AA2023Requests.submitWhichSchemeDetailsConfirmation("00348916RT"),
      AA2023Requests.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      AA2023Requests.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      AA2023Requests.navigateToDidYouPayAChargePage,
      AA2023Requests.submitDidYouPayAChargeConfirmation("true"),
      AA2023Requests.navigateToWhoPaidAnnualAllowanceChargePage,
      AA2023Requests.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      AA2023Requests.navigateToHowMuchYouPayChargePage,
      AA2023Requests.submitHowMuchYouPayChargeConfirmation("800"),
      AA2023Requests.navigateToAddAnotherSchemePage,
      AA2023Requests.submitAddAnotherSchemeConfirmation("true"),
      AA2023Requests.navigateToWhichSecondSchemeDetailsPage,
      AA2023Requests.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      AA2023Requests.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      AA2023Requests.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      AA2023Requests.navigateToDidYouPayAChargeSecondSchemePage,
      AA2023Requests.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      AA2023Requests.navigateToAddAnotherSchemeTwoPage,
      AA2023Requests.submitAddAnotherSchemeTwoConfirmation("false"),
      AA2023Requests.navigateToContributedOtherDbDcSchemePage,
      AA2023Requests.submitContributedOtherDbDcSchemeConfirmation("false"),
      AA2023Requests.navigateToThresholdIncomePage,
      AA2023Requests.submitThresholdIncomePageConfirmation("false"),
      AA2023Requests.submitTotalIncomePageUrlConfirmation("60000"),
      AA2023Requests.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Lifetime-Allowance", "Lifetime allowance")
    .withRequests(
      navigateToHadBenefitCrystallisationEventPage,
      submitHadBenefitCrystallisationEventConfirmation("true"),
      navigateToDateOfBenefitCrystallisationEventPage,
      submitDateOfBenefitCrystallisationEventConfirmation(),
      navigateToToldChangeInLtaPercentagePage,
      submitToldChangeInLtaPercentageConfirmation("true"),
      navigateToPercentageCausedChangeInChargePage,
      submitToPercentageCausedChangeInChargeConfirmation("newCharge"),
      navigateToLtaProtectionOrEnhancementsPage,
      submitLtaProtectionOrEnhancementsConfirmation("protection"),
      navigateToProtectionTypePage,
      submitProtectionTypePageConfirmation("enhancedProtection"),
      navigateToProtectionReferencePage,
      submitProtectionReferenceConfirmation("R41AB5678TR2335"),
      navigateToProtectionChangedPage,
      submitProtectionChangedConfirmation("true"),
      navigateToProtectionChangedNewTypePage,
      submitProtectionChangedNewTypeConfirmation("enhancedProtection"),
      navigateToProtectionChangedNewReferencePage,
      submitProtectionChangedNewReferenceConfirmation("R41AB5678TR2335"),
      navigateToLtaCharge20152023Page,
      submitLtaCharge20152023Confirmation("true"),
      navigateToHowExcessWasPaidPage,
      submitHowExcessWasPaidConfirmation("annualPayment"),
      navigateToHowMuchLtaChargePage,
      submitHowMuchLtaChargeConfirmation("200000000"),
      navigateToWhoPaidLtaChargePage,
      submitWhoPaidLtaChargeConfirmation("you"),
      navigateToValueNewLtaChargePage,
      submitValueNewLtaChargeConfirmation("100000000"),
      navigateToTaskListPageUrlPage
    )

  setup("Calculate-results", "Calculate results")
    .withRequests(
      navigateToCalculationResultPage,
      submitCalculationResultConfirmation()
    )

  setup("Auth-wizard", "Authorization")
    .withRequests(
      getSubmissionUniqueId(),
      loginForSubmission(),
      navigateToAuthPage
    )

  setup("Submission-route", "Submission")
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
      navigateToBankDetailsPage,
      submitBankDetailsPageConfirmation(),
      navigateToDeclarationsPage,
      navigateToCheckYourAnswersSubmitPage,
      navigateToAuthWizardSession,
      submitCheckYourAnswersSubmitPageConfirmation()
    )

  runSimulation()
}
