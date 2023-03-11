package com.ngapp.metanmobile.provider

interface NavigationProvider {
    fun openNews()
    fun openUpdateProfile()
    fun openStationDetail(stationCode: String)
    fun openNewsDetail(newsId: Int)
    fun openCabinet()
    fun openSettings()
    fun openAppLanguage()
    fun openAbout()
    fun openLegalRegulations()
    fun openTermsAndConditions()
    fun openPrivacyPolicy()
    fun openContacts()
    fun openFaq()
    fun openCalculators()
    fun openSoftwareLicense()
    fun openLocationInformation()
    fun navigateUp()
}