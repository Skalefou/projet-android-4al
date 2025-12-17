package com.groupe1.app_android.ui.screens.filterListing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.groupe1.app_android.ui.components.DateRangeHeadline
import com.groupe1.app_android.ui.theme.HoneyYellow
import com.groupe1.app_android.viewModels.FiltersViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterWhenScreen(
    viewModel: FiltersViewModel,
    onNext: () -> Unit,
    onBack: () -> Unit,
    onQuit: () -> Unit
) {
    val filters by viewModel.filters.collectAsState()
    val dateRangeState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = filters.checkIn,
        initialSelectedEndDateMillis = filters.checkOut
    )

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(25.dp, 50.dp, 25.dp, 50.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Quand partez-vous ?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                )

                IconButton(onClick = onQuit) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Quit"
                    )
                }
            }

            Spacer(Modifier.height(30.dp))

            // Calendar that updates dateRangeState
            Row(
                modifier = Modifier
                    .sizeIn(maxHeight = 660.dp)
                    .fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {


                        val selectableDates =
                            object : SelectableDates {
                                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                    return true
                                }

                                override fun isSelectableYear(year: Int): Boolean {
                                    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                                    return year >= currentYear
                                }
                            }

                        DateRangePicker(
                            state = dateRangeState,
                            modifier = Modifier.fillMaxWidth(),
                            title = {},
                            headline = {
                                DateRangeHeadline(
                                    startDate = dateRangeState.selectedStartDateMillis,
                                    endDate = dateRangeState.selectedEndDateMillis
                                )
                            },
                            showModeToggle = false,
                            colors = DatePickerDefaults.colors(
                                containerColor = Color.Transparent,
                                dividerColor = Color.Transparent,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                                headlineContentColor = MaterialTheme.colorScheme.onSurface,
                                weekdayContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                subheadContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )


                    }
                }
            }
            Spacer(Modifier.height(16.dp))

            // Next button
            Button(
                onClick = {
                    viewModel.updateDates(
                        dateRangeState.selectedStartDateMillis,
                        dateRangeState.selectedEndDateMillis
                    )
                    onNext()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HoneyYellow
                )
            ) {
                Text("Suivant")
            }
        }
    }
}


//@Preview
//@Composable
//fun FilterWhenScreenPreview_1() {
//    FilterWhenScreen()
//}
