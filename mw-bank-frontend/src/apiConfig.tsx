import { Configuration } from "./service";

const ApiConfig = () => (
    new Configuration({
        basePath: "http://localhost:9090",
        baseOptions: {
            withCredentials: true
        }
    })
)

export default ApiConfig;
