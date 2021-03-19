import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import org.scalatest._
import flatspec._
import matchers._

import scala.concurrent.{Await, TimeoutException}
import scala.concurrent.duration._

class AkkaStreamSpec extends AnyFlatSpec with should.Matchers with BeforeAndAfterAll
{
  implicit val actorSystem = ActorSystem("test")

  override def afterAll(): Unit = {
    actorSystem.terminate()
    super.afterAll()
  }

  "flow" should "timeout and rescue to 42" in {

    val future = Source.single(1)
      .map { i =>
        while (true) {
          // Do nothing.
        }
        i
      }
      .completionTimeout(1.seconds)
      .recover {
        case te: TimeoutException =>
          42
      }
      .runWith(Sink.head)

      Await.result(future, 10.seconds) should be (42)
  }
}