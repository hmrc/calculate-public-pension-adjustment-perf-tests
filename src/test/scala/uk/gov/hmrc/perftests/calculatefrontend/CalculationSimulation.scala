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
import uk.gov.hmrc.perftests.calculatefrontend.requests.submission.SubmissionRequests._
import uk.gov.hmrc.perftests.calculatefrontend.util.NINOGenerator

class CalculationSimulation extends PerformanceTestRunner {

  setup("Auth-wizard", "Authorization")
    .withRequests(
      getSubmissionUniqueId(),
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
