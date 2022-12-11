package com.toharifqi.sportagain.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.toharifqi.sportagain.core.domain.SportDomainData
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun SportFavoriteItem(
    modifier: Modifier = Modifier,
    sport: SportDomainData,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = 2.dp
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
                    .padding(vertical = 16.dp)
                    .size(80.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = sport.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = sport.format,
                    style = MaterialTheme.typography.body1.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            IconButton(
                onClick = {
                    onFavoriteClick()
                }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (isFavorite) "Delete from favorite" else "Add to favorite"
                )
            }
        }
    }
}

@Preview
@Composable
fun SportFavoriteItemPreview() {
    val dummySport = SportDomainData(
        "102",
        "Soccer",
        "TeamvsTeam",
        "https://www.thesportsdb.com/images/sports/soccer.jpg",
        "https://www.thesportsdb.com/images/icons/sports/soccer.png",
        "Association football, more commonly known as football or soccer, bla bla bla as you wish."
    )
    SportAgainTheme {
        SportFavoriteItem(
            sport = dummySport,
            isFavorite = false
        ) {

        }
    }
}
