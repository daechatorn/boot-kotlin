package org.man.bootkotlin.service

import org.man.bootkotlin.config.getLogger
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import java.util.concurrent.CompletableFuture


@Service
class ParallelService(val bootKotlinMockService: BootKotlinMockService) {

    private val log = getLogger { }

    fun testParallel() {

        val completableFutureV1 = CompletableFuture.supplyAsync { bootKotlinMockService.getBootKotlinMockV1Data() }
            .exceptionally { ex: Throwable ->
                if(ex.cause is HttpClientErrorException.BadRequest){
                    log.error("Error: {}", ex.message)
                }
                null //Response that we need to return in case of exception if we not handle, all of request will stop
            }

        val completableFutureV2 = CompletableFuture.supplyAsync { bootKotlinMockService.getBootKotlinMockV2Data() }
            .exceptionally { ex: Throwable ->
                if(ex.cause is HttpClientErrorException.BadRequest){
                    log.error("Error: {}", ex.message)
                }
                null //Response that we need to return in case of exception if we not handle, all of request will stop
            }

        CompletableFuture.allOf(completableFutureV1, completableFutureV2).join()

        log.info("Done with response completableFutureV1:{}, completableFutureV2:{}", completableFutureV1.get(), completableFutureV2.get())

    }

}

