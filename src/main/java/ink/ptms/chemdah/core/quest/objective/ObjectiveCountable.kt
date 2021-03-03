package ink.ptms.chemdah.core.quest.objective

import ink.ptms.chemdah.core.quest.PlayerProfile
import ink.ptms.chemdah.core.quest.Progress
import ink.ptms.chemdah.core.quest.Progress.Companion.to
import ink.ptms.chemdah.core.quest.Task
import org.bukkit.event.Event

/**
 * Chemdah
 * ink.ptms.chemdah.core.quest.objective.OCountable
 *
 * @author sky
 * @since 2021/3/1 11:53 下午
 */
abstract class ObjectiveCountable<E : Event> : Objective<E>() {

    init {
        addGoal { profile, task ->
            profile.metadata(task) {
                get("amount", 0).toInt() >= task.goal.get("amount", 1).toInt()
            }
        }
    }

    override fun onContinue(profile: PlayerProfile, task: Task, event: Event) {
        profile.metadata(task) {
            put("amount", get("amount", 0).toInt() + 1)
        }
    }

    override fun progress(profile: PlayerProfile, task: Task): Progress {
        val target = task.goal.get("amount", 1).toInt()
        return if (hasCompletedSignature(profile, task)) {
            target.to(target, 1.0)
        } else {
            profile.metadata(task) {
                get("amount", 0).toInt().let { a -> a.to(target, a / target.toDouble()) }
            }
        }
    }
}