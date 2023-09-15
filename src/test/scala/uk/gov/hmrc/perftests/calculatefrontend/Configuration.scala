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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.check.HttpCheck
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait Configuration extends ServicesConfiguration {

  val calculationUrl: String                  = baseUrlFor("calculate-stub")
  private val csrfTokenPattern: String        = """<input type="hidden" name="csrfToken"\s+value="([^"]+)""""
  private val totalIncreaseTaxPattern: String =
    """<th scope="row" class="govuk-table__header">Total increase in tax charges<\/th>\s+<td class="govuk-table__cell">(\d+)<\/td>"""
  def saveCsrfToken: HttpCheck                =
    regex(_ => csrfTokenPattern).saveAs("csrfToken")

  def saveTotIncreaseTax: HttpCheck =
    regex(_ => totalIncreaseTaxPattern).find.saveAs("totIncreaseTax")
}
