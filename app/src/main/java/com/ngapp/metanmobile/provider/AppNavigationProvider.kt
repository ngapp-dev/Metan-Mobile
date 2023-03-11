package com.ngapp.metanmobile.provider

import androidx.navigation.NavController
import com.ngapp.metanmobile.presentation.destinations.*
import com.ramcosta.composedestinations.navigation.navigateTo

class AppNavigationProvider constructor(
    private val navController: NavController
) : NavigationProvider {

    override fun openNews() {
        navController.navigateTo(NewsScreenDestination)
    }

    override fun openUpdateProfile() {
        navController.navigateTo(ProfileScreenDestination)
    }

    override fun openStationDetail(stationCode: String) {
        navController.navigateTo(StationDetailScreenDestination(stationCode))
    }

    override fun openNewsDetail(newsId: Int) {
        navController.navigateTo(NewsDetailScreenDestination(newsId))
    }

    override fun openCabinet() {
        navController.navigateTo(CabinetScreenDestination())
    }

    override fun openSettings() {
        navController.navigateTo(SettingsScreenDestination)
    }

    override fun openAppLanguage() {
        navController.navigateTo(LanguageScreenDestination)
    }

    override fun openAbout() {
        navController.navigateTo(AboutScreenDestination)
    }

    override fun openLegalRegulations() {
        navController.navigateTo(LegalRegulationsScreenDestination)
    }

    override fun openTermsAndConditions() {
        navController.navigateTo(TermsAndConditionsScreenDestination())
    }

    override fun openPrivacyPolicy() {
        navController.navigateTo(PrivacyPolicyScreenDestination())
    }

    override fun openFaq() {
        navController.navigateTo(FaqScreenDestination)
    }

    override fun openCalculators() {
        navController.navigateTo(CalculatorsScreenDestination)
    }

    override fun openSoftwareLicense() {
        navController.navigateTo(SoftwareLicenseScreenDestination)
    }

    override fun openLocationInformation() {
        navController.navigateTo(LocationInformationScreenDestination)
    }

    override fun openContacts() {
        navController.navigateTo(ContactsScreenDestination)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}