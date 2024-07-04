package example.support.jwt

import java.security.Key

interface SignatureKey {
    val key: Key
}
