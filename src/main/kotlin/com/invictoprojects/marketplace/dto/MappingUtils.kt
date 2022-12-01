package com.invictoprojects.marketplace.dto

import com.invictoprojects.marketplace.dto.user.AuthorSaleDto
import com.invictoprojects.marketplace.dto.user.AvatarDto
import com.invictoprojects.marketplace.dto.user.BannerDto
import com.invictoprojects.marketplace.dto.user.BidDto
import com.invictoprojects.marketplace.persistence.model.Category
import com.invictoprojects.marketplace.persistence.model.Order
import com.invictoprojects.marketplace.persistence.model.OrderProduct
import com.invictoprojects.marketplace.persistence.model.OrderProductKey
import com.invictoprojects.marketplace.persistence.model.OrderStatus
import com.invictoprojects.marketplace.persistence.model.Product
import com.invictoprojects.marketplace.persistence.model.Review
import com.invictoprojects.marketplace.persistence.model.User
import com.invictoprojects.marketplace.persistence.model.user.UserInformation
import com.invictoprojects.marketplace.persistence.model.user.extended.*

object MappingUtils {

    fun convertToEntity(categoryCreationDto: CategoryCreationDto): Category {
        return Category(
            name = categoryCreationDto.name
        )
    }

    private fun convertToEntity(categoryDto: CategoryDto): Category {
        return Category(
            id = categoryDto.id,
            name = categoryDto.name
        )
    }

    private fun convertToEntity(productDto: ProductDto): Product {
        return Product(
            id = productDto.id,
            name = productDto.name,
            description = productDto.description,
            imagePath = productDto.imagePath,
            category = convertToEntity(productDto.category),
            seller = productDto.seller,
            price = productDto.price,
            quantity = productDto.quantity
        )
    }

    fun convertToEntity(productCreationDto: ProductCreationDto): Product {
        return Product(
            name = productCreationDto.name,
            description = productCreationDto.description,
            imagePath = productCreationDto.imagePath,
            category = convertToEntity(productCreationDto.category),
            seller = productCreationDto.seller,
            price = productCreationDto.price,
            quantity = productCreationDto.quantity
        )
    }

    fun convertToEntity(userDto: UserDto): User {
        return User(
            username = userDto.username,
            email = userDto.email,
            subscribed = userDto.subscribed
        )
    }

    private fun convertToDto(user: User): UserDto {
        return UserDto(
            username = user.username,
            email = user.email,
            subscribed = user.subscribed
        )
    }

    fun convertToDto(category: Category): CategoryDto {
        return CategoryDto(
            id = category.id!!,
            name = category.name
        )
    }

    fun convertToDto(product: Product): ProductDto {
        return ProductDto(
            id = product.id!!,
            name = product.name,
            description = product.description,
            imagePath = product.imagePath,
            category = convertToDto(product.category),
            seller = product.seller,
            price = product.price,
            quantity = product.quantity,
            avgRating = product.avgRating,
            ratingCount = product.ratingCount,
            reviews = product.reviews?.map { convertToDto(it) }?.toMutableList()
        )
    }

    fun convertToEntity(userInfo: UserInformationDto): UserInformation {
        return UserInformation(
        username = userInfo.username,
        social = userInfo.social,
        wallet = userInfo.wallet,
        followers = userInfo.followers,
        about = userInfo.about,

        published_at = userInfo.published_at,
        user_information_id = userInfo.user_information_id,

        )
    }
//ToDo Implement these and their mapping
    
    fun convertToEntity(avatarDto: AvatarDto): Avatar {
        return Avatar(
             name= avatarDto.name,
             alternativeText= avatarDto.alternativeText,
             caption= avatarDto.caption,
             width= avatarDto.width,
             height= avatarDto.height,
             hash= avatarDto.hash,
             ext= avatarDto.ext,
             mime= avatarDto.mime,
             size= avatarDto.size,
             url= avatarDto.url,
             previewUrl= avatarDto.previewUrl,
             provider= avatarDto.provider,
             provider_metadata= avatarDto.provider_metadata,
             formats = avatarDto.formats,
        )
    }

    fun convertToEntity(banner: BannerDto): Banner {
        return Banner(
            name= banner.name,
            alternativeText= banner.alternativeText,
            caption= banner.caption,
            width= banner.width,
            height= banner.height,
            hash= banner.hash,
            ext= banner.ext,
            mime= banner.mime,
            size= banner.size,
            url= banner.url,
            previewUrl= banner.previewUrl,
            provider= banner.provider,
            provider_metadata= banner.provider_metadata,
            formats = banner.formats,
        )
    }
    
    fun convertToEntity(bid: BidDto): Bid {
        return Bid(
        BidId = bid.BidId,
        value = bid.value,
        nft = bid.nft,
        author = bid.author,
        published_at = bid.published_at,
        userInformation = bid.userInformation,
        )
    }

    fun convertToEntity(authorSale: AuthorSaleDto): AuthorSale {
        return AuthorSale(
        sales = authorSale.sales,
        volume = authorSale.volume,
        daily_sales = authorSale.daily_sales,
        weekly_sales = authorSale.weekly_sales,
        floor_price = authorSale.floor_price,
        owners = authorSale.owners,
        assets = authorSale.assets,
        authorId = authorSale.authorId,
        userInformation =  authorSale.userInformation,
        )
    }

    fun convertToDto(review: Review): ReviewDto {
        return ReviewDto(
            author = convertToDto(review.author!!),
            rating = review.rating,
            date = review.date,
            content = review.content
        )
    }

    fun convertToEntity(reviewCreationDto: ReviewCreationDto): Review {
        return Review(
        rating = reviewCreationDto.rating,
        content = reviewCreationDto.content
    )
    }

    private fun convertToEntity(orderDto: OrderDto): Order {
        return Order(
            id = orderDto.id,
            customer = convertToEntity(orderDto.customer),
            status = convertToEntity(orderDto.status),
            date = orderDto.date,
            destination = orderDto.destination,
            orderProducts = orderDto.orderProducts.map { orderDetailDto -> convertToEntity(orderDetailDto) }.toMutableList()
        )
    }

    fun convertToEntity(orderCreationDto: OrderCreationDto): Order {
        return Order(
            customer = convertToEntity(orderCreationDto.customer),
            status = convertToEntity(orderCreationDto.status),
            date = orderCreationDto.date,
            destination = orderCreationDto.destination
        )
    }

    fun convertToDto(order: Order): OrderDto {
        return OrderDto(
            id = order.id!!,
            customer = convertToDto(order.customer),
            status = convertToDto(order.status),
            date = order.date,
            destination = order.destination,
            orderProducts = order.orderProducts.map { orderProduct -> convertToDto(orderProduct) }.toMutableList()
        )
    }

    private fun convertToEntity(orderStatusDto: OrderStatusDto): OrderStatus {
        return OrderStatus.valueOf(orderStatusDto.status.uppercase())
    }

    private fun convertToDto(orderStatus: OrderStatus): OrderStatusDto {
        return OrderStatusDto(
            status = orderStatus.toString()
        )
    }

    private fun convertToEntity(orderDetailDto: OrderDetailDto): OrderProduct {
        return OrderProduct(
            id = OrderProductKey(
                orderDetailDto.order.id,
                orderDetailDto.product.id
            ),
            order = convertToEntity(orderDetailDto.order),
            product = convertToEntity(orderDetailDto.product),
            amount = orderDetailDto.amount
        )
    }

    fun convertToDto(orderProduct: OrderProduct): OrderDetailDto {
        return OrderDetailDto(
            order = convertToDto(orderProduct.order),
            product = convertToDto(orderProduct.product),
            amount = orderProduct.amount
        )
    }
}
