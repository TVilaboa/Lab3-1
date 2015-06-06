package Application

import Domain.Found
import Infrastucture.FoundRepository

/**
 * Created by Tom�s on 05/06/2015.
 */
class FoundService {
    private var repo = new FoundRepository

  def Get(Id : Integer): Found ={
    repo.Get(Id)
  }

  def AddOrUpdate(Found : Found): Unit ={
    if (Found.Id == 0)
    repo.Add(Found)
    else
    repo.Update(Found)
  }
}
