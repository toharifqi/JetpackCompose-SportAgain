package com.toharifqi.sportagain.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.toharifqi.sportagain.R
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SportItem(
    modifier: Modifier = Modifier,
    sport: SportDomainData,
    onClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 48.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = sport.icon,
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .padding(16.dp)
                    .size(animatedSizeDp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = sport.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                AnimatedVisibility(
                    visible = isExpanded,
                    enter = fadeIn()
                ) {
                    Text(
                        text = stringResource(
                            if (sport.format == "TeamvsTeam")
                                R.string.sport_format_team
                            else
                                R.string.sport_format_event
                        ),
                    )
                }
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.Close else Icons.Outlined.Info,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
        }
    }
}

@Preview
@Composable
fun SportItemPreview() {
    val dummySport = SportDomainData(
        "102",
        "Soccer",
        "TeamvsTeam",
        "https://www.thesportsdb.com/images/sports/soccer.jpg",
        "https://www.thesportsdb.com/images/icons/sports/soccer.png",
        "Association football, more commonly known as football or soccer, bla bla bla as you wish."
    )
    SportAgainTheme {
        SportItem(sport = dummySport) {

        }
    }
}
