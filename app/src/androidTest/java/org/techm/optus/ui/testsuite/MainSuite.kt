package org.techm.optus.ui.testsuite

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.techm.optus.ui.view.album.FragmentAlbumDetailsTest
import org.techm.optus.ui.view.album.FragmentAlbumTest
import org.techm.optus.ui.view.user.FragmentUserTest


@RunWith(Suite::class)
@Suite.SuiteClasses(
    FragmentUserTest::class,
    FragmentAlbumTest::class,
    FragmentAlbumDetailsTest::class
)
class MainSuite {
}