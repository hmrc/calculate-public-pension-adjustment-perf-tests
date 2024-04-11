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

package uk.gov.hmrc.perftests.calculatefrontend.requests.auth

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.perftests.calculatefrontend.Configuration

import scala.io.Source

object AuthRequests extends Configuration {

  val authWizardUrl: String        = s"$authUrl/auth-login-stub/gg-sign-in"
  val submissionUrl: String        = s"$sessionUrl/calculate-public-pension-adjustment/submission"
  val redirectionUrl: String       =
    s"$submissionFrontendUrl/submit-public-pension-adjustment/landing-page?submissionUniqueId="
  val jsonPayload                  = Source.fromResource("data/calculateFrontend.json").getLines().mkString
  val authWizardSessionUrl: String = s"$authUrl/auth-login-stub/session"

  def getSubmissionUniqueId(): HttpRequestBuilder =
    http("get submission id")
      .post(submissionUrl)
      .header("Content-Type", "application/json")
      .body(StringBody(jsonPayload))
      .check(status.is(202))
      .check(jsonPath("$.uniqueId").saveAs("submissionUniqueId"))
      .silent

  def getSubmission(): HttpRequestBuilder =
    http("GG sign in")
      .post(authWizardUrl)
      .formParam("redirectionUrl", authWizardSessionUrl)
      .formParam("excludeGnapToken", "true")
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "250")
      .formParam("affinityGroup", "Individual")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("nino", "AA000000A")
      .formParam("itmp.givenName", "Lari")
      .formParam("authorityId", "")
      .check(status.is(303))
      .silent

  def loginForSubmission(): HttpRequestBuilder =
    http("Log in with submissionUniqueId")
      .post(authWizardUrl)
      .formParam("authorityId", "")
      .formParam("gatewayToken", "")
      .formParam("redirectionUrl", redirectionUrl + "${submissionUniqueId}")
      .formParam("excludeGnapToken", "false")
      .formParam("credentialStrength", "strong")
      .formParam("confidenceLevel", "250")
      .formParam("affinityGroup", "Individual")
      .formParam("usersName", "")
      .formParam("email", "user@test.com")
      .formParam("credentialRole", "User")
      .formParam("oauthTokens.accessToken", "")
      .formParam("oauthTokens.refreshToken", "")
      .formParam("oauthTokens.idToken", "")
      .formParam("additionalInfo.profile", "")
      .formParam("additionalInfo.groupProfile", "")
      .formParam("additionalInfo.emailVerified", "N/A")
      .formParam("nino", "AA000000A")
      .formParam("groupIdentifier", "")
      .formParam("agent.agentId", "")
      .formParam("agent.agentCode", "")
      .formParam("agent.agentFriendlyName", "")
      .formParam("unreadMessageCount", "")
      .formParam("mdtp.sessionId", "")
      .formParam("mdtp.deviceId", "")
      .formParam("presets-dropdown", "IR-SA")
      .formParam("enrolment[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].name", "")
      .formParam("enrolment[0].taxIdentifier[0].value", "")
      .formParam("enrolment[0].state", "Activated")
      .formParam("enrolment[1].name", "")
      .formParam("enrolment[1].taxIdentifier[0].name", "")
      .formParam("enrolment[1].taxIdentifier[0].value", "")
      .formParam("enrolment[1].state", "Activated")
      .formParam("enrolment[2].name", "")
      .formParam("enrolment[2].taxIdentifier[0].name", "")
      .formParam("enrolment[2].taxIdentifier[0].value", "")
      .formParam("enrolment[2].state", "Activated")
      .formParam("enrolment[3].name", "")
      .formParam("enrolment[3].taxIdentifier[0].name", "")
      .formParam("enrolment[3].taxIdentifier[0].value", "")
      .formParam("enrolment[3].state", "Activated")
      .formParam("enrolment[4].name", "IR-SA")
      .formParam("enrolment[4].taxIdentifier[0].name", "UTR")
      .formParam("enrolment[4].taxIdentifier[0].value", "123456789")
      .formParam("enrolment[4].state", "Activated")
      .formParam("itmp.givenName", "Lari")
      .formParam("itmp.middleName", "Tharu")
      .formParam("itmp.familyName", "Wije")
      .formParam("itmp.dateOfBirth", "1950-09-09")
      .formParam("itmp.address.line1", "")
      .formParam("itmp.address.line2", "")
      .formParam("itmp.address.line3", "")
      .formParam("itmp.address.line4", "")
      .formParam("itmp.address.line5", "")
      .formParam("itmp.address.postCode", "")
      .formParam("itmp.address.countryName", "")
      .formParam("itmp.address.countryCode", "")
      .check(status.is(303))
      .silent

  val navigateToAuthPage: HttpRequestBuilder =
    http("Navigate to auth page ")
      .get(redirectionUrl + "${submissionUniqueId}")
      .check(status.is(303))
      .silent

  def getSubmissionBearerToken(): HttpRequestBuilder =
    http("getSubmission id")
      .get(authWizardSessionUrl)
      .check(status.is(200))
      .check(saveBearerToken)
      .silent

}
