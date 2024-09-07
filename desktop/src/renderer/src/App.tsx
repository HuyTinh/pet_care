import { RouterProvider } from 'react-router-dom'
import { RouterHooks } from './router'

function App(): JSX.Element {
  const hookRouter = RouterHooks()
  return <RouterProvider router={hookRouter.router} />
}

export default App
