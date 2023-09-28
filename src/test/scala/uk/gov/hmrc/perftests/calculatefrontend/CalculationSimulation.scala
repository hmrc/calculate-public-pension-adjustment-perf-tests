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
import uk.gov.hmrc.perftests.calculatefrontend.requests.auth.AuthRequests.{getSubmissionUniqueId, loginForSubmission, navigateToAuthPage}
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.AAWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.BasicWorkFlowRequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA.CalculationRequests.{navigateToCalculationResultPage, submitCalculationResultConfirmation}
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateAA._
import uk.gov.hmrc.perftests.calculatefrontend.requests.calculateLTA.LTARequests._
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SessionRequests.navigateToAuthWizardSession
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests._
import uk.gov.hmrc.perftests.calculatefrontend.util.NINOGenerator

class CalculationSimulation extends PerformanceTestRunner {
  val pension_scheme_name = "pensionSchemeName"
  val taxRef = "00348916RX"

  val periodRequests2016Pre = new AAPeriodRequests("2016-pre")
  val periodRequests2016Post = new AAPeriodRequests("2016-post")
  val periodRequests2017 = new AAPeriodRequests("2017")
  val periodRequests2018 = new AAPeriodRequests("2018")
  val periodRequests2019 = new AAPeriodRequests("2019")
  val periodRequests2020 = new AAPeriodRequests("2020")
  val periodRequests2021 = new AAPeriodRequests("2021")
  val periodRequests2022 = new AAPeriodRequests("2022")
  val periodRequests2023 = new AAPeriodRequests("2023")

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
      periodRequests2016Pre.navigateToWhatYouWillNeedAaPage,
      periodRequests2016Pre.navigateToMemberMoreThanOnePensionPage,
      periodRequests2016Pre.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2016Pre.navigateToFirstPensionSchemeDetailsPage,
      periodRequests2016Pre.submitPensionFirstSchemeDetailsConfirmation("Scheme 1", "00348916RT"),
      periodRequests2016Pre.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016Pre.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("20000", "15000"),
      periodRequests2016Pre.navigateToDidYouPayAChargePage,
      periodRequests2016Pre.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2016Pre.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2016Pre.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2016Pre.navigateToHowMuchYouPayChargePage,
      periodRequests2016Pre.submitHowMuchYouPayChargeConfirmation("3200"),
      periodRequests2016Pre.navigateToAddAnotherSchemePage,
      periodRequests2016Pre.submitAddAnotherSchemeConfirmation("false"),
      periodRequests2016Pre.navigateToSecondPensionSchemeDetailsPage,
      periodRequests2016Pre.submitPensionSecondSchemeDetailsConfirmation("Scheme 2", "00348916RG"),
      periodRequests2016Pre.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016Pre.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("20000", "18000"),
      periodRequests2016Pre.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2016Pre.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2016Pre.navigateToAddAnotherSchemeTwoPage,
      periodRequests2016Pre.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2016Pre.navigateToContributedOtherDbDcSchemePage,
      periodRequests2016Pre.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2016Pre.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2016Pre.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2016Pre.navigateToPiaForDbPensionPage,
      periodRequests2016Pre.submitPiaForDbPensionConfirmation("33000"),
      periodRequests2016Pre.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2016Post", "2016 post calculation")
    .withRequests(
      periodRequests2016Post.navigateToWhatYouWillNeedAaPage,
      periodRequests2016Post.navigateToMemberMoreThanOnePensionPage,
      periodRequests2016Post.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2016Post.navigateToWhichSchemeDetailsPage,
      periodRequests2016Post.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2016Post.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016Post.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("30000", "25000"),
      periodRequests2016Post.navigateToDidYouPayAChargePage,
      periodRequests2016Post.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2016Post.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2016Post.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2016Post.navigateToHowMuchYouPayChargePage,
      periodRequests2016Post.submitHowMuchYouPayChargeConfirmation("3200"),
      periodRequests2016Post.navigateToAddAnotherSchemePage,
      periodRequests2016Post.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2016Post.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2016Post.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2016Post.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2016Post.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("18000", "15000"),
      periodRequests2016Post.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2016Post.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2016Post.navigateToAddAnotherSchemeTwoPage,
      periodRequests2016Post.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2016Post.navigateToContributedOtherDbDcSchemePage,
      periodRequests2016Post.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2016Post.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2016Post.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2016Post.navigateToPiaForDbPensionPage,
      periodRequests2016Post.submitPiaForDbPensionConfirmation("40000"),
      periodRequests2016Post.navigateToTotalIncomePageUrlPage,
      periodRequests2016Post.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2016Post.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2017", "2017 calculation")
    .withRequests(
      periodRequests2017.navigateToWhatYouWillNeedAaPage,
      periodRequests2017.navigateToMemberMoreThanOnePensionPage,
      periodRequests2017.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2017.navigateToWhichSchemeDetailsPage,
      periodRequests2017.submitWhichSchemeDetailsConfirmation("00348916RT"),
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
      periodRequests2017.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2018", "2018 calculation")
    .withRequests(
      periodRequests2018.navigateToWhatYouWillNeedAaPage,
      periodRequests2018.navigateToMemberMoreThanOnePensionPage,
      periodRequests2018.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2018.navigateToWhichSchemeDetailsPage,
      periodRequests2018.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2018.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2018.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2018.navigateToDidYouPayAChargePage,
      periodRequests2018.submitDidYouPayAChargeConfirmation("false"),
      periodRequests2018.navigateToAddAnotherSchemePage,
      periodRequests2018.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2018.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2018.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2018.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2018.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("30000", "25000"),
      periodRequests2018.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2018.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2018.navigateToAddAnotherSchemeTwoPage,
      periodRequests2018.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2018.navigateToContributedOtherDbDcSchemePage,
      periodRequests2018.submitContributedOtherDbDcSchemeConfirmation("true"),
      periodRequests2018.navigateToWhichContributedDuringRemedyPeriodPage,
      periodRequests2018.submitWhichContributedDuringRemedyPeriodConfirmation("definedBenefit"),
      periodRequests2018.navigateToPiaForDbPensionPage,
      periodRequests2018.submitPiaForDbPensionConfirmation("38000"),
      periodRequests2018.navigateToThresholdIncomePage,
      periodRequests2018.submitThresholdIncomePageConfirmation("false"),
      periodRequests2018.navigateToTotalIncomePageUrlPage,
      periodRequests2018.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2018.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2019", "2019 calculation")
    .withRequests(
      periodRequests2019.navigateToWhatYouWillNeedAaPage,
      periodRequests2019.navigateToMemberMoreThanOnePensionPage,
      periodRequests2019.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2019.navigateToWhichSchemeDetailsPage,
      periodRequests2019.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2019.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2019.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2019.navigateToDidYouPayAChargePage,
      periodRequests2019.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2019.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2019.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2019.navigateToHowMuchYouPayChargePage,
      periodRequests2019.submitHowMuchYouPayChargeConfirmation("3280"),
      periodRequests2019.navigateToAddAnotherSchemePage,
      periodRequests2019.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2019.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2019.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2019.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2019.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("48000", "43000"),
      periodRequests2019.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2019.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2019.navigateToAddAnotherSchemeTwoPage,
      periodRequests2019.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2019.navigateToContributedOtherDbDcSchemePage,
      periodRequests2019.submitContributedOtherDbDcSchemeConfirmation("false"),
      periodRequests2019.navigateToThresholdIncomePage,
      periodRequests2019.submitThresholdIncomePageConfirmation("false"),
      periodRequests2019.navigateToTotalIncomePageUrlPage,
      periodRequests2019.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2019.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2020", "2020 calculation")
    .withRequests(
      periodRequests2020.navigateToWhatYouWillNeedAaPage,
      periodRequests2020.navigateToMemberMoreThanOnePensionPage,
      periodRequests2020.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2020.navigateToWhichSchemeDetailsPage,
      periodRequests2020.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2020.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2020.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2020.navigateToDidYouPayAChargePage,
      periodRequests2020.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2020.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2020.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2020.navigateToHowMuchYouPayChargePage,
      periodRequests2020.submitHowMuchYouPayChargeConfirmation("800"),
      periodRequests2020.navigateToAddAnotherSchemePage,
      periodRequests2020.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2020.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2020.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2020.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2020.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      periodRequests2020.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2020.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2020.navigateToAddAnotherSchemeTwoPage,
      periodRequests2020.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2020.navigateToContributedOtherDbDcSchemePage,
      periodRequests2020.submitContributedOtherDbDcSchemeConfirmation("false"),
      periodRequests2020.navigateToThresholdIncomePage,
      periodRequests2020.submitThresholdIncomePageConfirmation("false"),
      periodRequests2020.navigateToTotalIncomePageUrlPage,
      periodRequests2020.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2020.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2021", "2021 calculation")
    .withRequests(
      periodRequests2021.navigateToWhatYouWillNeedAaPage,
      periodRequests2021.navigateToMemberMoreThanOnePensionPage,
      periodRequests2021.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2021.navigateToWhichSchemeDetailsPage,
      periodRequests2021.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2021.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2021.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2021.navigateToDidYouPayAChargePage,
      periodRequests2021.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2021.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2021.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2021.navigateToHowMuchYouPayChargePage,
      periodRequests2021.submitHowMuchYouPayChargeConfirmation("800"),
      periodRequests2021.navigateToAddAnotherSchemePage,
      periodRequests2021.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2021.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2021.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2021.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2021.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      periodRequests2021.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2021.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2021.navigateToAddAnotherSchemeTwoPage,
      periodRequests2021.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2021.navigateToContributedOtherDbDcSchemePage,
      periodRequests2021.submitContributedOtherDbDcSchemeConfirmation("false"),
      periodRequests2021.navigateToThresholdIncomePage,
      periodRequests2021.submitThresholdIncomePageConfirmation("false"),
      periodRequests2021.navigateToTotalIncomePageUrlPage,
      periodRequests2021.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2021.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2022", "2022 calculation")
    .withRequests(
      periodRequests2022.navigateToWhatYouWillNeedAaPage,
      periodRequests2022.navigateToMemberMoreThanOnePensionPage,
      periodRequests2022.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2022.navigateToWhichSchemeDetailsPage,
      periodRequests2022.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2022.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2022.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2022.navigateToDidYouPayAChargePage,
      periodRequests2022.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2022.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2022.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2022.navigateToHowMuchYouPayChargePage,
      periodRequests2022.submitHowMuchYouPayChargeConfirmation("800"),
      periodRequests2022.navigateToAddAnotherSchemePage,
      periodRequests2022.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2022.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2022.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2022.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2022.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      periodRequests2022.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2022.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2022.navigateToAddAnotherSchemeTwoPage,
      periodRequests2022.submitAddAnotherSchemeTwoConfirmation("true"),
      periodRequests2022.navigateToWhichThirdSchemeDetailsPage,
      periodRequests2022.submitWhichThirdSchemeDetailsConfirmation("New"),
      periodRequests2022.navigateToThirdPensionSchemeDetailsPage,
      periodRequests2022.submitPensionThirdSchemeDetailsConfirmation("Scheme 3", "00348916RF"),
      periodRequests2022.navigateToThirdPensionSchemeInputAmountsPageUrlPage,
      periodRequests2022.submitPensionThirdSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      periodRequests2022.navigateToDidYouPayAChargeThirdSchemePage,
      periodRequests2022.submitDidYouPayAChargeThirdSchemeConfirmation("false"),
      periodRequests2022.navigateToAddAnotherSchemeThreePage,
      periodRequests2022.submitAddAnotherSchemeThreeConfirmation("false"),
      periodRequests2022.navigateToContributedOtherDbDcSchemePage,
      periodRequests2022.submitContributedOtherDbDcSchemeConfirmation("false"),
      periodRequests2022.navigateToThresholdIncomePage,
      periodRequests2022.submitThresholdIncomePageConfirmation("false"),
      periodRequests2022.navigateToTotalIncomePageUrlPage,
      periodRequests2022.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2022.navigateToCheckYourAnswersPeriodPage,
      navigateToTaskListPageUrlPage
    )

  setup("Annual-Allowance-2023", "2023 calculation")
    .withRequests(
      periodRequests2023.navigateToWhatYouWillNeedAaPage,
      periodRequests2023.navigateToMemberMoreThanOnePensionPage,
      periodRequests2023.submitMemberMoreThanOnePensionConfirmation("true"),
      periodRequests2023.navigateToWhichSchemeDetailsPage,
      periodRequests2023.submitWhichSchemeDetailsConfirmation("00348916RT"),
      periodRequests2023.navigateToFirstPensionSchemeInputAmountsPageUrlPage,
      periodRequests2023.submitFirstPensionSchemeInputAmountsPageUrlConfirmation("0", "0"),
      periodRequests2023.navigateToDidYouPayAChargePage,
      periodRequests2023.submitDidYouPayAChargeConfirmation("true"),
      periodRequests2023.navigateToWhoPaidAnnualAllowanceChargePage,
      periodRequests2023.submitWhoPaidAnnualAllowanceChargeConfirmation("you"),
      periodRequests2023.navigateToHowMuchYouPayChargePage,
      periodRequests2023.submitHowMuchYouPayChargeConfirmation("800"),
      periodRequests2023.navigateToAddAnotherSchemePage,
      periodRequests2023.submitAddAnotherSchemeConfirmation("true"),
      periodRequests2023.navigateToWhichSecondSchemeDetailsPage,
      periodRequests2023.submitWhichSecondSchemeDetailsConfirmation("00348916RG"),
      periodRequests2023.navigateToSecondPensionSchemeInputAmountsPageUrlPage,
      periodRequests2023.submitPensionSecondSchemeInputAmountsPageUrlConfirmation("42000", "40000"),
      periodRequests2023.navigateToDidYouPayAChargeSecondSchemePage,
      periodRequests2023.submitDidYouPayAChargeSecondSchemeConfirmation("false"),
      periodRequests2023.navigateToAddAnotherSchemeTwoPage,
      periodRequests2023.submitAddAnotherSchemeTwoConfirmation("false"),
      periodRequests2023.navigateToContributedOtherDbDcSchemePage,
      periodRequests2023.submitContributedOtherDbDcSchemeConfirmation("false"),
      periodRequests2023.navigateToThresholdIncomePage,
      periodRequests2023.submitThresholdIncomePageConfirmation("false"),
      periodRequests2023.submitTotalIncomePageUrlConfirmation("60000"),
      periodRequests2023.navigateToCheckYourAnswersPeriodPage,
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
      navigateToMoreThanOneBenefitCrystallisationEventPage,
      submitToMoreThanOneBenefitCrystallisationEventConfirmation("false"),
      navigateToLtaProtectionOrEnhancementsPage,
      submitLtaProtectionOrEnhancementsConfirmation("enhancements"),
      navigateToEnhancementTypePage,
      submitEnhancementTypePageConfirmation("both"),
      navigateToInternationalEnhancementReferencePage,
      submitInternationalEnhancementReferencePageConfirmation("1234567890ASDFG"),
      navigateToPensionCreditReferencePage,
      submitPensionCreditReferencePageConfirmation("1234567890ZXCVB"),
      navigateToProtectionEnhancementChangedPage,
      submitProtectionEnhancementChangedConfirmation("both"),
      navigateToProtectionChangedNewTypePage,
      submitProtectionChangedNewTypeConfirmation("enhancedProtection"),
      navigateToProtectionChangedNewReferencePage,
      submitProtectionChangedNewReferenceConfirmation("R41AB5678TR2335"),
      navigateToNewEnhancementTypePage,
      submitNewEnhancementTypeConfirmation("both"),
      navigateToNewInternationalEnhancementReferencePage,
      submitNewInternationalEnhancementReferenceConfirmation("1234567891QWERT"),
      navigateToNewPensionCreditReferencePage,
      submitNewPensionCreditReferenceConfirmation("1234567123QAZXS"),
      navigateToLtaCharge20152023Page,
      submitLtaCharge20152023Confirmation("true"),
      navigateToHowExcessWasPaidPage,
      submitHowExcessWasPaidConfirmation("both"),
      navigateToValueOfLumpSumPage,
      submitValueOfLumpSumPageConfirmation("1000"),
      navigateToValueOfAnnualPaymentPage,
      submitValueOfAnnualPaymentPageUrlConfirmation("3000"),
      navigateToWhoPaidLtaChargePage,
      submitWhoPaidLtaChargeConfirmation("pensionScheme"),
      navigateToSchemePaidLtaChargePage,
      submitSchemePaidLtaChargePageUrlConfirmation(pension_scheme_name, taxRef),
      navigateToQuarterChargeWasPaidPage,
      submitQuarterChargeWasPaidPageConfirmation("aprToJul"),
      navigateToYearChargeWasPaidPage,
      submitYearChargeWasPaidConfirmation("2021To2022"),
      navigateToNewExcessPaidPage,
      submitNewExcessPaidPageConfirmation("both"),
      navigateToNewValueOfLumpSumPage,
      submitNewValueOfLumpSumPageConfirmation("2300"),
      navigateToNewValueOfAnnualPaymentPage,
      submitNewValueOfAnnualPaymentPageConfirmation("2000"),
      navigateToWhoPaidExtraChargePage,
      submitWhoPaidExtraChargePageConfirmation("pensionScheme"),
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
      navigateToDeclarationsPage,
      navigateToCheckYourAnswersSubmitPage,
      navigateToAuthWizardSession,
      submitCheckYourAnswersSubmitPageConfirmation()
    )

  runSimulation()
}
