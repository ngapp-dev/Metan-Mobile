package com.ngapp.metanmobile.presentation.home.view.pinnedNews

sealed class PinnedNewsPage(
    val title: String,
    val date: String
) {
    object First : PinnedNewsPage(
        title = "News 1",
        date = "Here is the first news."
    )

    object Second : PinnedNewsPage(
        title = "News 2",
        date = "Here is the second news."
    )

    object Third : PinnedNewsPage(
        title = "News 3",
        date = "Here is the third news."
    )
}