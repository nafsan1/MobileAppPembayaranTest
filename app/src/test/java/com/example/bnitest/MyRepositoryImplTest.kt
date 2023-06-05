package com.example.bnitest

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MyRepositoryImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var pref: PreferencesRepository

    @Inject
    lateinit var myRepository: MyRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
    }

    @Test
    fun getPromosTest() = runBlockingTest {
        // Given
        val promoList = mutableListOf<PromoResponseItem>()
        whenever(remoteDataSource.getPromos()).thenReturn(flowOf(ApiResponse.Success(promoList)))

        // When
        val result = myRepository.getPromos().first()

        // Then
        assertTrue(result is Resource.Success)
        assertEquals(result.data, promoList)
    }
}