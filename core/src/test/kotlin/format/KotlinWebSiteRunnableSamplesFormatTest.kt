package org.jetbrains.dokka.tests

import org.jetbrains.dokka.KotlinLanguageService
import org.jetbrains.dokka.KotlinWebsiteRunnableSamplesFormatService
import org.junit.Test

class KotlinWebSiteRunnableSamplesFormatTest {
    private val kwsService = KotlinWebsiteRunnableSamplesFormatService(InMemoryLocationService, KotlinLanguageService())

    @Test fun sample() {
        verifyKWSNodeByName("sample", "foo")
    }

    @Test fun sampleWithAsserts() {
        verifyKWSNodeByName("sampleWithAsserts", "a")
    }

    private fun verifyKWSNodeByName(fileName: String, name: String) {
        verifyOutput("testdata/format/website-samples/$fileName.kt", ".md", format = "kotlin-website-samples") { model, output ->
            kwsService.createOutputBuilder(output, tempLocation).appendNodes(model.members.single().members.filter { it.name == name })
        }
    }
}
