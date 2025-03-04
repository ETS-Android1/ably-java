package io.ably.lib.types;

import io.ably.lib.push.Storage;
import io.ably.lib.rest.Auth.AuthOptions;
import io.ably.lib.rest.Auth.TokenParams;
import io.ably.lib.transport.Defaults;
import io.ably.lib.util.Log;
import io.ably.lib.util.Log.LogHandler;

import java.util.Map;

/**
 * Options: Ably library options for REST and Realtime APIs
 */
public class ClientOptions extends AuthOptions {

    /**
     * Default constructor
     */
    public ClientOptions() {}

    /**
     * Construct an options with a single key string. The key string is obtained
     * from the application dashboard.
     * @param key the key string
     * @throws AblyException if the key is not in a valid format
     */
    public ClientOptions(String key) throws AblyException {
        super(key);
        logLevel = Log.defaultLevel;
    }

    /**
     * The id of the client represented by this instance. The clientId is relevant
     * to presence operations, where the clientId is the principal identifier of the
     * client in presence update messages. The clientId is also relevant to
     * authentication; a token issued for a specific client may be used to authenticate
     * the bearer of that token to the service.
     */
    public String clientId;

    /**
     * Log level; controls the level of verbosity of log messages from the library.
     */
    public int logLevel;

    /**
     * Log handler: allows the client to intercept log messages and handle them in a
     * client-specific way.
     */
    public LogHandler logHandler;


    /**
     * Encrypted transport: if true, TLS will be used for all connections (whether REST/HTTP
     * or Realtime WebSocket or Comet connections).
     */
    public boolean tls = true;

    /**
     * FIXME: unused
     */
    public Map<String, String> headers;

    /**
     * For development environments only; allows a non-default Ably host to be specified.
     */
    public String restHost;

    /**
     * For development environments only; allows a non-default Ably host to be specified for
     * websocket connections.
     */
    public String realtimeHost;

    /**
     * For development environments only; allows a non-default Ably port to be specified.
     */
    public int port;

    /**
     * For development environments only; allows a non-default Ably TLS port to be specified.
     */
    public int tlsPort;

    /**
     * If false, suppresses the automatic initiation of a connection when the library is instanced.
     */
    public boolean autoConnect = true;

    /**
     * If false, forces the library to use the JSON encoding for REST and Realtime operations,
     * instead of the default binary msgpack encoding.
     */
    public boolean useBinaryProtocol = true;

    /**
     * If false, suppresses the default queueing of messages when connection states that
     * anticipate imminent connection (connecting and disconnected). Instead, publish and
     * presence state changes will fail immediately if not in the connected state.
     */
    public boolean queueMessages = true;

    /**
     * If false, suppresses messages originating from this connection being echoed back
     * on the same connection.
     */
    public boolean echoMessages = true;

    /**
     * A connection recovery string, specified by a client when initialising the library
     * with the intention of inheriting the state of an earlier connection. See the Ably
     * Realtime API documentation for further information on connection state recovery.
     */
    public String recover;

    /**
     * Proxy settings
     */
    public ProxyOptions proxy;

    /**
     * For development environments only; allows a non-default Ably environment
     * to be used such as 'sandbox'.
     * Spec: TO3k1
     */
    public String environment;

    /**
     * Spec: TO3n
     */
    public boolean idempotentRestPublishing = (Defaults.ABLY_VERSION_NUMBER >= 1.2);

    /**
     * Spec: TO313
     */
    public int httpOpenTimeout = Defaults.TIMEOUT_HTTP_OPEN;

    /**
     * Spec: TO314
     */
    public int httpRequestTimeout = Defaults.TIMEOUT_HTTP_REQUEST;

    /**
     * Max number of fallback hosts to use as a fallback when an HTTP request to
     * the primary host is unreachable or indicates that it is unserviceable
     */
    public int httpMaxRetryCount = Defaults.HTTP_MAX_RETRY_COUNT;

    /**
     * Spec: DF1b
     */
    public long realtimeRequestTimeout = Defaults.realtimeRequestTimeout;

    /**
     * Spec: TO3k6,RSC15a,RSC15b,RTN17b list of custom fallback hosts.
     */
    public String[] fallbackHosts;

    /**
     * Spec: TO3k7 Set to use default fallbackHosts even when overriding
     * environment or restHost/realtimeHost
     */
    @Deprecated
    public boolean fallbackHostsUseDefault;

    /**
     * Spec: TO3l10
     */
    public long fallbackRetryTimeout = Defaults.fallbackRetryTimeout;
    /**
     * When a TokenParams object is provided, it will override
     * the client library defaults described in TokenParams
     * Spec: TO3j11
     */
    public TokenParams defaultTokenParams = new TokenParams();

    /**
     * Channel reattach timeout
     * Spec: RTL13b
     */
    public int channelRetryTimeout = Defaults.TIMEOUT_CHANNEL_RETRY;

    /**
     * Additional parameters to be sent in the querystring when initiating a realtime connection
     */
    public Param[] transportParams;

    /**
     * Allows the caller to specify a non-default size for the asyncHttp threadpool
     */
    public int asyncHttpThreadpoolSize = Defaults.HTTP_ASYNC_THREADPOOL_SIZE;

    /**
     * Whether to tell Ably to wait for push REST requests to fully wait for all their effects
     * before responding.
     */
    public boolean pushFullWait = false;

    /**
     * Custom Local Device storage. In the case nothing is provided then a default implementation
     * using SharedPreferences is used.
     */
    public Storage localStorage = null;

    /**
     If enabled, every REST request to Ably includes a `request_id` query string parameter. This request ID
     remain the same if a request is retried to a fallback host.
     */
    public boolean addRequestIds = false;

    /**
     * Map of agents that will be appended to the agent header.
     *
     * This should only be used by Ably-authored SDKs.
     * If you need to use this then you have to add the agent to the agents.json file:
     * https://github.com/ably/ably-common/blob/main/protocol/agents.json
     *
     * The keys represent agent names and its corresponding values represent agent versions.
     * Agent versions are optional, if you don't want to specify it pass `null` as the map entry value.
     */
    public Map<String, String> agents;
}
